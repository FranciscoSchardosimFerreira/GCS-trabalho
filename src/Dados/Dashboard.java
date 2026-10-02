package Dados;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.TreeMap;

public class Dashboard {

    private SistemaCustos sistemaCustos;
    private SistemaPessoas sistemaPessoas;

    public Dashboard(SistemaCustos sistemaCustos, SistemaPessoas sistemaPessoas) {
        this.sistemaCustos = sistemaCustos;
        this.sistemaPessoas = sistemaPessoas;
    }

    public int getQuantidadeFuncionarios() {
        return sistemaPessoas.getFuncionarios().size();
    }

    public int getQuantidadeCustos() {
        return sistemaCustos.getCustos().size();
    }

    public double getTotalCustos() {
        double total = 0;
        for (Custo c : sistemaCustos.getCustos()) {
            total += c.getValor();
        }
        return total;
    }

    public double getMediaCustos() {
        if (getQuantidadeCustos() == 0) {
            return 0;
        }
        return getTotalCustos() / getQuantidadeCustos();
    }

    public double getMedianaCustos() {
        ArrayList<Double> valores = new ArrayList<>();
        for (Custo c : sistemaCustos.getCustos()) {
            valores.add(c.getValor());
        }
        if (valores.isEmpty()) {
            return 0;
        }
        Collections.sort(valores);
        int meio = valores.size() / 2;
        if (valores.size() % 2 == 0) {
            return (valores.get(meio - 1) + valores.get(meio)) / 2;
        }
        return valores.get(meio);
    }

    public Custo getMaiorCusto() {
        Custo maior = null;
        for (Custo c : sistemaCustos.getCustos()) {
            if (maior == null || c.getValor() > maior.getValor()) {
                maior = c;
            }
        }
        return maior;
    }

    public Custo getMenorCusto() {
        Custo menor = null;
        for (Custo c : sistemaCustos.getCustos()) {
            if (menor == null || c.getValor() < menor.getValor()) {
                menor = c;
            }
        }
        return menor;
    }

    public Custo getCustoMaisRecente() {
        Custo recente = null;
        for (Custo c : sistemaCustos.getCustos()) {
            if (recente == null || c.getData().isAfter(recente.getData())) {
                recente = c;
            }
        }
        return recente;
    }

    public EnumMap<Departamento, Double> getTotalPorDepartamento() {
        EnumMap<Departamento, Double> totais = new EnumMap<>(Departamento.class);
        for (Departamento d : Departamento.values()) {
            totais.put(d, 0.0);
        }
        for (Custo c : sistemaCustos.getCustos()) {
            totais.put(c.getDepartamento(), totais.get(c.getDepartamento()) + c.getValor());
        }
        return totais;
    }

    public EnumMap<Departamento, Integer> getQuantidadePorDepartamento() {
        EnumMap<Departamento, Integer> quantidades = new EnumMap<>(Departamento.class);
        for (Departamento d : Departamento.values()) {
            quantidades.put(d, 0);
        }
        for (Custo c : sistemaCustos.getCustos()) {
            quantidades.put(c.getDepartamento(), quantidades.get(c.getDepartamento()) + 1);
        }
        return quantidades;
    }

    public EnumMap<CustoCategoria, Double> getTotalPorCategoria() {
        EnumMap<CustoCategoria, Double> totais = new EnumMap<>(CustoCategoria.class);
        for (CustoCategoria cat : CustoCategoria.values()) {
            totais.put(cat, 0.0);
        }
        for (Custo c : sistemaCustos.getCustos()) {
            totais.put(c.getCusotCategoria(), totais.get(c.getCusotCategoria()) + c.getValor());
        }
        return totais;
    }

    public TreeMap<YearMonth, Double> getTotalPorMes() {
        TreeMap<YearMonth, Double> totais = new TreeMap<>();
        for (Custo c : sistemaCustos.getCustos()) {
            totais.merge(YearMonth.from(c.getData()), c.getValor(), Double::sum);
        }
        return totais;
    }

    public double getPercentual(double valor) {
        double total = getTotalCustos();
        if (total == 0) {
            return 0;
        }
        return valor / total * 100;
    }

    public Departamento getDepartamentoComMaiorCusto() {
        return chaveComMaiorValor(getTotalPorDepartamento());
    }

    public CustoCategoria getCategoriaComMaiorCusto() {
        return chaveComMaiorValor(getTotalPorCategoria());
    }

    // retorna null quando não há custos cadastrados
    private <K> K chaveComMaiorValor(Map<K, Double> totais) {
        K maior = null;
        double maiorValor = 0;
        for (var entrada : totais.entrySet()) {
            if (entrada.getValue() > maiorValor) {
                maior = entrada.getKey();
                maiorValor = entrada.getValue();
            }
        }
        return maior;
    }

    public void mostrarResumoPorCategoria() {
        System.out.println("\nRESUMO POR CATEGORIA");
        for (var entrada : getTotalPorCategoria().entrySet()) {
            System.out.printf("%-20s R$ %.2f%n", entrada.getKey(), entrada.getValue());
        }
    }

    public void mostrarEstatisticas() {
        System.out.println("========= ESTATÍSTICAS =========");

        if (getQuantidadeCustos() == 0) {
            System.out.println("Nenhum custo cadastrado.");
            System.out.println("================================");
            return;
        }

        System.out.printf("Mediana dos custos: R$ %.2f%n", getMedianaCustos());
        Custo menor = getMenorCusto();
        System.out.printf("Menor custo: %s (R$ %.2f)%n", menor.getDescricao(), menor.getValor());
        System.out.println("Departamento com maior gasto: " + getDepartamentoComMaiorCusto());
        System.out.println("Categoria com maior gasto: " + getCategoriaComMaiorCusto());
        System.out.println();

        System.out.println("Departamentos (qtd. / total / % do total):");
        EnumMap<Departamento, Integer> quantidades = getQuantidadePorDepartamento();
        for (var entrada : getTotalPorDepartamento().entrySet()) {
            System.out.printf("  %-18s %3d   R$ %10.2f   %5.1f%%%n",
                    entrada.getKey(), quantidades.get(entrada.getKey()),
                    entrada.getValue(), getPercentual(entrada.getValue()));
        }
        System.out.println();

        System.out.println("Categorias (total / % do total):");
        for (var entrada : getTotalPorCategoria().entrySet()) {
            System.out.printf("  %-18s R$ %10.2f   %5.1f%%%n",
                    entrada.getKey(), entrada.getValue(), getPercentual(entrada.getValue()));
        }
        System.out.println();

        System.out.println("Total por mês:");
        for (var entrada : getTotalPorMes().entrySet()) {
            System.out.printf("  %-18s R$ %10.2f%n", entrada.getKey(), entrada.getValue());
        }

        System.out.println("================================");
    }

    public void mostrar() {
        System.out.println("========== DASHBOARD ==========");

        Funcionario atual = sistemaPessoas.getFuncionarioAtual();
        System.out.println("Funcionário atual: " + (atual != null ? atual.getNome() : "nenhum"));
        System.out.println("Funcionários cadastrados: " + getQuantidadeFuncionarios());
        System.out.println();

        System.out.println("Custos registrados: " + getQuantidadeCustos());
        System.out.printf("Total dos custos: R$ %.2f%n", getTotalCustos());
        System.out.printf("Média por custo: R$ %.2f%n", getMediaCustos());

        Custo maior = getMaiorCusto();
        if (maior != null) {
            System.out.printf("Maior custo: %s (R$ %.2f)%n", maior.getDescricao(), maior.getValor());
        }
        Custo recente = getCustoMaisRecente();
        if (recente != null) {
            System.out.println("Custo mais recente: " + recente.getDescricao() + " (" + recente.getData() + ")");
        }
        System.out.println();

        System.out.println("Total por departamento:");
        for (var entrada : getTotalPorDepartamento().entrySet()) {
            System.out.printf("  %-18s R$ %.2f%n", entrada.getKey(), entrada.getValue());
        }
        System.out.println();

        System.out.println("Total por categoria:");
        for (var entrada : getTotalPorCategoria().entrySet()) {
            System.out.printf("  %-18s R$ %.2f%n", entrada.getKey(), entrada.getValue());
        }

        System.out.println("===============================");
    }
}

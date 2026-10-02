package Dados;

import java.util.EnumMap;

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

    public Custo getMaiorCusto() {
        Custo maior = null;
        for (Custo c : sistemaCustos.getCustos()) {
            if (maior == null || c.getValor() > maior.getValor()) {
                maior = c;
            }
        }
        return maior;
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

    public void mostrarResumoPorCategoria() {
        System.out.println("\nRESUMO POR CATEGORIA");
        for (var entrada : getTotalPorCategoria().entrySet()) {
            System.out.printf("%-20s R$ %.2f%n", entrada.getKey(), entrada.getValue());
        }
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

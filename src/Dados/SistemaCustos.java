package Dados;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class SistemaCustos {

    private ArrayList<Custo> custos;

    public SistemaCustos(ArrayList<Custo> custos) {
        this.custos = custos;
    }

    public void adicionarCusto(Custo c) {
        if (c == null) {
            throw new IllegalArgumentException("O custo não pode ser nulo.");
        }
        custos.add(c);
    }

    // O custo é sempre registrado em nome do funcionário atualmente logado
    public void cadastrarCusto(Scanner sc, Funcionario funcionarioAtual) {

        if (funcionarioAtual == null) {
            System.out.println("Selecione um operador antes de registrar custos.");
            return;
        }

        System.out.println("\n===== CADASTRO DE CUSTO =====");
        System.out.println("Registrado por: " + funcionarioAtual.getNome()
                + " (" + funcionarioAtual.mostrarIniciais() + ")");

        // Valor
        double valor;

        while (true) {
            System.out.print("Valor (R$): ");

            try {
                String texto = sc.nextLine().trim().replace("R$", "").trim();
                // aceita 1.250,90 ou 1250.90
                if (texto.contains(",")) {
                    texto = texto.replace(".", "").replace(",", ".");
                }
                valor = Double.parseDouble(texto);

                if (valor <= 0) {
                    System.out.println("O valor deve ser maior que zero.");
                    continue;
                }

                break;

            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite apenas números.");
            }
        }

        // Descrição
        String descricao = Entrada.lerTexto(sc, "Descrição: ");

        // Data
        LocalDate data = Entrada.lerData(sc, "Data (DD/MM/AAAA): ", false);

        // Departamento
        Departamento departamento = Entrada.escolher(sc, "\nDepartamentos:", Departamento.values());

        // Categoria
        CustoCategoria categoria = Entrada.escolher(sc, "\nCategorias:", CustoCategoria.values());

        // Criação do custo
        Custo custo = new Custo(
                valor,
                descricao,
                data,
                departamento,
                categoria,
                funcionarioAtual
        );

        adicionarCusto(custo);

        System.out.println("\nCusto cadastrado com sucesso!");
        System.out.println(custo);
    }

    // Lista do mais recente ao mais antigo (requisito 5)
    public void printCustos(ArrayList<Custo> custosImpressos) {

        if (custosImpressos.isEmpty()) {
            System.out.println("Nenhum custo encontrado.");
            return;
        }

        ArrayList<Custo> listagem = new ArrayList<>(custosImpressos);
        listagem.sort(Collections.reverseOrder());

        for (Custo i : listagem) {
            System.out.println(i);
        }

        double total = 0;
        for (Custo c : listagem) {
            total += c.getValor();
        }
        System.out.println(listagem.size() + " registro(s) | Total: " + Entrada.formatarValor(total));
    }

    public void printCustos(ArrayList<Custo> custosImpressos, CriterioOrdenacao criterio, boolean crescente) {

        for (Custo i : ordenarCustos(custosImpressos, criterio, crescente)) {
            System.out.println(i);
        }
    }

    // devolve uma nova lista, sem alterar a ordem da lista original
    public ArrayList<Custo> ordenarCustos(ArrayList<Custo> lista, CriterioOrdenacao criterio, boolean crescente) {

        ArrayList<Custo> ordenados = new ArrayList<>(lista);

        Comparator<Custo> comparador = criterio.getComparador();

        if (!crescente) {
            comparador = comparador.reversed();
        }

        ordenados.sort(comparador);

        return ordenados;
    }

    public void listarCustosOrdenados(Scanner sc) {

        if (custos.isEmpty()) {
            System.out.println("Não existem custos cadastrados.");
            return;
        }

        CriterioOrdenacao criterio = Entrada.escolher(sc, "\nOrdenar por:", CriterioOrdenacao.values());

        boolean crescente;

        while (true) {
            System.out.print("1 - Crescente | 2 - Decrescente: ");

            String opcao = sc.nextLine().trim();

            if (opcao.equals("1") || opcao.equals("2")) {
                crescente = opcao.equals("1");
                break;
            }

            System.out.println("Opção inválida.");
        }

        System.out.println("\n===== CUSTOS ORDENADOS POR " + criterio.getNome().toUpperCase()
                + (crescente ? " (CRESCENTE)" : " (DECRESCENTE)") + " =====");

        printCustos(custos, criterio, crescente);
    }

    public Custo getCustoMaisRecente() {
        if (custos.isEmpty()) {
            return null;
        }
        return Collections.max(custos);
    }

    // Requisito 6: só o registro mais recente pode ser excluído.
    // Não recebe parâmetro de propósito: não existe forma de pedir a exclusão de outro registro.
    public Custo excluirCustoMaisRecente() {

        Custo custoRecente = getCustoMaisRecente();

        if (custoRecente == null) {
            System.out.println("Não existem custos cadastrados.");
            return null;
        }

        custos.remove(custoRecente);
        return custoRecente;
    }

    public ArrayList<Custo> acharCustoPorDescricao(String descricao) {

        ArrayList<Custo> custosAchados = new ArrayList<>();
        String termo = descricao.trim().toLowerCase();

        for (Custo i : custos) {

            if (i.getDescricao().toLowerCase().contains(termo)) {
                custosAchados.add(i);
            }
        }

        printCustos(custosAchados);

        return custosAchados;
    }

    public ArrayList<Custo> acharCustoPorCategoria(CustoCategoria cat) {

        ArrayList<Custo> custosAchados = new ArrayList<>();

        for (Custo i : custos) {

            if (i.getCusotCategoria() == cat) {
                custosAchados.add(i);
            }
        }

        printCustos(custosAchados);

        return custosAchados;
    }

    public ArrayList<Custo> acharCustoPorDepartamento(Departamento dpt) {

        ArrayList<Custo> custosAchados = new ArrayList<>();

        for (Custo i : custos) {

            if (i.getDepartamento() == dpt) {
                custosAchados.add(i);
            }
        }

        printCustos(custosAchados);

        return custosAchados;
    }

    public ArrayList<Custo> acharCustoPorData(LocalDate data) {

        ArrayList<Custo> custosAchados = new ArrayList<>();

        for (Custo i : custos) {

            if (i.getData().equals(data)) {
                custosAchados.add(i);
            }
        }

        printCustos(custosAchados);

        return custosAchados;
    }

    // Menu de pesquisa (requisito 5)
    public void pesquisarCustos(Scanner sc) {

        System.out.println("\n===== PESQUISAR CUSTOS =====");
        System.out.println("1 - Por descrição");
        System.out.println("2 - Por categoria");
        System.out.println("3 - Por data");
        System.out.println("4 - Por departamento");
        System.out.println("0 - Voltar");

        int opcao = Entrada.lerInteiro(sc, "Escolha: ", 0, 4);

        switch (opcao) {
            case 1 -> {
                String termo = Entrada.lerTexto(sc, "Texto a pesquisar na descrição: ");
                System.out.println("\nResultados para \"" + termo + "\" (mais recente primeiro):");
                acharCustoPorDescricao(termo);
            }
            case 2 -> {
                CustoCategoria cat = Entrada.escolher(sc, "Categorias:", CustoCategoria.values());
                System.out.println("\nCustos da categoria " + cat + " (mais recente primeiro):");
                acharCustoPorCategoria(cat);
            }
            case 3 -> {
                LocalDate data = Entrada.lerData(sc, "Data (DD/MM/AAAA): ", true);
                System.out.println("\nCustos de " + Entrada.formatarData(data) + ":");
                acharCustoPorData(data);
            }
            case 4 -> {
                Departamento dpt = Entrada.escolher(sc, "Departamentos:", Departamento.values());
                System.out.println("\nCustos do departamento " + dpt + " (mais recente primeiro):");
                acharCustoPorDepartamento(dpt);
            }
            default -> {
                // 0 - voltar
            }
        }
    }

    public void sortarPorData(ArrayList<Custo> custosDatados) {
        Collections.sort(custosDatados);
    }

    public ArrayList<Custo> getCustos() {
        return custos;
    }

    public void listarTodosCustos() {

        if (custos.isEmpty()) {
            System.out.println("Nenhum custo cadastrado.");
            return;
        }

        System.out.println("\n===== LISTAGEM GERAL DE CUSTOS (mais recente primeiro) =====");

        printCustos(custos);
    }

    public ArrayList<Custo> relatorioPorPeriodo(LocalDate inicio, LocalDate fim) {

        ArrayList<Custo> custosPeriodo = new ArrayList<>();

        for (Custo custo : custos) {
            if (!custo.getData().isBefore(inicio)
                    && !custo.getData().isAfter(fim)) {

                custosPeriodo.add(custo);
            }
        }

        sortarPorData(custosPeriodo);

        return custosPeriodo;
    }

    public void mostrarRelatorioPorPeriodo(LocalDate inicio, LocalDate fim) {

        ArrayList<Custo> resultado = relatorioPorPeriodo(inicio, fim);

        System.out.println("\nRELATÓRIO POR PERÍODO");
        System.out.println("Período: " + Entrada.formatarData(inicio) + " até " + Entrada.formatarData(fim));

        if (resultado.isEmpty()) {
            System.out.println("Nenhum custo encontrado nesse período.");
            return;
        }

        double total = 0;

        for (Custo custo : resultado) {
            System.out.println(custo);
            total += custo.getValor();
        }

        System.out.println("Total do período: " + Entrada.formatarValor(total));
    }

    public void mostrarRelatorioPorPeriodo(Scanner sc) {

        LocalDate inicio = Entrada.lerData(sc, "Data inicial (DD/MM/AAAA): ", true);
        LocalDate fim;

        while (true) {
            fim = Entrada.lerData(sc, "Data final (DD/MM/AAAA): ", true);
            if (!fim.isBefore(inicio)) {
                break;
            }
            System.out.println("A data final não pode ser anterior à inicial.");
        }

        mostrarRelatorioPorPeriodo(inicio, fim);
    }
}

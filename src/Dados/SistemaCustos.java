package Dados;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
        custos.add(c);
    }

    public void cadastrarCusto(Scanner sc) {

        System.out.println("\n===== CADASTRO DE CUSTO =====");

        // Valor
        double valor;

        while (true) {
            System.out.print("Valor: ");

            try {
                valor = Double.parseDouble(sc.nextLine().trim().replace(",", "."));

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
        String descricao;

        do {
            System.out.print("Descrição: ");
            descricao = sc.nextLine().trim();

            if (descricao.isEmpty()) {
                System.out.println("A descrição não pode ser vazia.");
            }

        } while (descricao.isEmpty());

        // Data
        LocalDate data;

        while (true) {
            System.out.print("Data (AAAA-MM-DD): ");

            try {
                data = LocalDate.parse(sc.nextLine().trim());
                break;

            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Use o formato AAAA-MM-DD.");
            }
        }

        // Departamento
        Departamento[] departamentos = Departamento.values();

        System.out.println("\nDepartamentos:");

        for (int i = 0; i < departamentos.length; i++) {
            System.out.println((i + 1) + " - " + departamentos[i]);
        }

        Departamento departamento;

        while (true) {
            System.out.print("Escolha o departamento: ");

            try {
                int opcao = Integer.parseInt(sc.nextLine().trim());

                if (opcao >= 1 && opcao <= departamentos.length) {
                    departamento = departamentos[opcao - 1];
                    break;
                }

            } catch (NumberFormatException e) {
                // Continua para a mensagem de erro
            }

            System.out.println("Opção inválida.");
        }

        // Categoria
        CustoCategoria[] categorias = CustoCategoria.values();

        System.out.println("\nCategorias:");

        for (int i = 0; i < categorias.length; i++) {
            System.out.println((i + 1) + " - " + categorias[i]);
        }

        CustoCategoria categoria;

        while (true) {
            System.out.print("Escolha a categoria: ");

            try {
                int opcao = Integer.parseInt(sc.nextLine().trim());

                if (opcao >= 1 && opcao <= categorias.length) {
                    categoria = categorias[opcao - 1];
                    break;
                }

            } catch (NumberFormatException e) {
                // Continua para a mensagem de erro
            }

            System.out.println("Opção inválida.");
        }

        // Criação do custo
        Custo custo = new Custo(
                valor,
                descricao,
                data,
                departamento,
                categoria
        );

        adicionarCusto(custo);

        System.out.println("\nCusto cadastrado com sucesso!");
        System.out.println(custo);
    }

    public void printCustos(ArrayList<Custo> custosImpressos) {

        sortarPorData(custosImpressos);

        for (Custo i : custosImpressos) {
            System.out.println(i);
        }
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

        CriterioOrdenacao[] criterios = CriterioOrdenacao.values();

        System.out.println("\nOrdenar por:");

        for (int i = 0; i < criterios.length; i++) {
            System.out.println((i + 1) + " - " + criterios[i]);
        }

        CriterioOrdenacao criterio;

        while (true) {
            System.out.print("Escolha o critério: ");

            try {
                int opcao = Integer.parseInt(sc.nextLine().trim());

                if (opcao >= 1 && opcao <= criterios.length) {
                    criterio = criterios[opcao - 1];
                    break;
                }

            } catch (NumberFormatException e) {
                // Continua para a mensagem de erro
            }

            System.out.println("Opção inválida.");
        }

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

    public void excluirCustoMaisRecente() {

        sortarPorData(custos);

        if (custos.isEmpty()) {
            System.out.println("Não existem custos cadastrados.");
            return;
        }

        Custo custoRecente = custos.getLast();

        custos.remove(custoRecente);
    }

    public ArrayList<Custo> acharCustoPorDescricao(String descricao) {

        ArrayList<Custo> custosAchados = new ArrayList<>();

        for (Custo i : custos) {

            if (i.getDescricao().contains(descricao)) {
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

    public void sortarPorData(ArrayList<Custo> custosDatados) {
        Collections.sort(custosDatados);
    }

    public ArrayList<Custo> getCustos() {
        return custos;
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
        System.out.println("Período: " + inicio + " até " + fim);

        if (resultado.isEmpty()) {
            System.out.println("Nenhum custo encontrado nesse período.");
            return;
        }

        double total = 0;

        for (Custo custo : resultado) {
            System.out.println(custo);
            total += custo.getValor();
        }

        System.out.printf("Total do período: R$ %.2f%n", total);
    }
}

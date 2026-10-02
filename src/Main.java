import Dados.Custo;
import Dados.DadosIniciais;
import Dados.Dashboard;
import Dados.Entrada;
import Dados.Funcionario;
import Dados.SistemaCustos;
import Dados.SistemaPessoas;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        SistemaPessoas sistemaPessoas = new SistemaPessoas(new ArrayList<>());
        SistemaCustos sistemaCustos = new SistemaCustos(new ArrayList<>());
        Dashboard dashboard = new Dashboard(sistemaCustos, sistemaPessoas);

        // dados de exemplo para facilitar os testes
        DadosIniciais.carregar(sistemaPessoas, sistemaCustos);

        System.out.println("=================================================");
        System.out.println("     SISTEMA DE GERÊNCIA DE CUSTOS - EMPRESA");
        System.out.println("=================================================");

        try {
            // Requisito 1: escolher quem está usando o sistema
            sistemaPessoas.selecionarFuncionario(sc);

            boolean executando = true;

            while (executando) {
                mostrarMenu(sistemaPessoas.getFuncionarioAtual());
                int opcao = Entrada.lerInteiro(sc, "Escolha uma opção: ", 0, 12);

                try {
                    switch (opcao) {
                        case 1 -> sistemaPessoas.selecionarFuncionario(sc);
                        case 2 -> sistemaPessoas.cadastrarFuncionario(sc);
                        case 3 -> {
                            System.out.println("\n===== FUNCIONÁRIOS =====");
                            sistemaPessoas.printFuncionarios();
                        }
                        case 4 -> sistemaPessoas.listarDepartamentos();
                        case 5 -> sistemaCustos.cadastrarCusto(sc, sistemaPessoas.getFuncionarioAtual());
                        case 6 -> sistemaCustos.pesquisarCustos(sc);
                        case 7 -> sistemaCustos.listarTodosCustos();
                        case 8 -> excluirMaisRecente(sc, sistemaCustos);
                        case 9 -> dashboard.mostrar();
                        case 10 -> dashboard.mostrarEstatisticas();
                        case 11 -> sistemaCustos.listarCustosOrdenados(sc);
                        case 12 -> sistemaCustos.mostrarRelatorioPorPeriodo(sc);
                        case 0 -> executando = !Entrada.confirmar(sc, "Deseja realmente sair?");
                        default -> System.out.println("Opção inválida.");
                    }
                } catch (IllegalArgumentException e) {
                    // validações das classes de dados não derrubam o programa
                    System.out.println("Erro: " + e.getMessage());
                }

                if (executando && opcao != 0) {
                    System.out.print("\nPressione ENTER para voltar ao menu...");
                    sc.nextLine();
                }
            }
        } catch (NoSuchElementException e) {
            // entrada encerrada (ex.: Ctrl+D / Ctrl+Z)
            System.out.println();
        }

        System.out.println("Sistema encerrado. Até logo!");
        sc.close();
    }

    private static void mostrarMenu(Funcionario atual) {
        System.out.println("\n=================== MENU PRINCIPAL ===================");
        System.out.println("Operador: " + (atual != null
                ? atual.getNome() + " (" + atual.mostrarIniciais() + ") - " + atual.getDepartamento()
                : "nenhum selecionado"));
        System.out.println("------------------------------------------------------");
        System.out.println(" FUNCIONÁRIOS");
        System.out.println("  1 - Trocar operador (funcionário logado)");
        System.out.println("  2 - Cadastrar novo funcionário");
        System.out.println("  3 - Listar funcionários");
        System.out.println("  4 - Listar departamentos");
        System.out.println(" CUSTOS");
        System.out.println("  5 - Registrar novo custo");
        System.out.println("  6 - Pesquisar custos");
        System.out.println("  7 - Listar todos os custos");
        System.out.println("  8 - Excluir o custo mais recente");
        System.out.println(" PAINEL E RELATÓRIOS");
        System.out.println("  9 - Painel geral");
        System.out.println(" 10 - Estatísticas detalhadas");
        System.out.println(" 11 - Listar custos ordenados por critério");
        System.out.println(" 12 - Relatório por período");
        System.out.println("");
        System.out.println("  0 - Sair");
        System.out.println("======================================================");
    }

    private static void excluirMaisRecente(Scanner sc, SistemaCustos sistemaCustos) {
        Custo recente = sistemaCustos.getCustoMaisRecente();
        if (recente == null) {
            System.out.println("Não existem custos cadastrados.");
            return;
        }
        System.out.println("\nSomente o registro mais recente pode ser excluído:");
        System.out.println(recente);
        if (Entrada.confirmar(sc, "Confirma a exclusão?")) {
            sistemaCustos.excluirCustoMaisRecente();
            System.out.println("Custo excluído com sucesso.");
        } else {
            System.out.println("Exclusão cancelada.");
        }
    }
}

import Dados.Custo;
import Dados.CustoCategoria;
import Dados.Dashboard;
import Dados.Departamento;
import Dados.Funcionario;
import Dados.SistemaCustos;
import Dados.SistemaPessoas;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Sistema de pessoas
        SistemaPessoas sistemaPessoas =
                new SistemaPessoas(new ArrayList<>());

        Funcionario alexandre =
                new Funcionario(1, "Alexandre", Departamento.RH);

        Funcionario bianca =
                new Funcionario(2, "Bianca", Departamento.VENDAS);

        sistemaPessoas.adicionarFuncionario(alexandre);
        sistemaPessoas.adicionarFuncionario(bianca);

        // Sistema de custos
        SistemaCustos sistemaCustos =
                new SistemaCustos(new ArrayList<>());

        // Cadastro de custo
        sistemaCustos.cadastrarCusto(sc);

        // Exibição dos custos cadastrados
        System.out.println("\n===== CUSTOS CADASTRADOS =====");

        sistemaCustos.acharCustoPorDescricao("");

        // Dashboard
        Dashboard dashboard = new Dashboard(sistemaCustos, sistemaPessoas);

        System.out.println();
        dashboard.mostrar();

        System.out.println();
        dashboard.mostrarEstatisticas();

        sc.close();
    }
}
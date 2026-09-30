import Dados.*;
import Dados.Departamento;
import Dados.Funcionario;
import Dados.SistemaPessoas;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main{
    public static void main(String[] args) {

    //ArrayList<Funcionario> funcionarios;
    Funcionario Alexandre = new Funcionario(1, "Alexandre", Departamento.RH);
    Funcionario Bianca = new Funcionario(2, "Bianca", Departamento.VENDAS);
    Custo TvGrande = new Custo(12.0, "tv grande",  LocalDate.of(1988, 9, 29), Departamento.COMPRAS, CustoCategoria.OUTROSSERVICOS);
    Custo TvPequena = new Custo(13.0, "tv pequena",  LocalDate.of(1988, 9, 13), Departamento.RH, CustoCategoria.AQUSISCAODEBENS);
    Custo Balde = new Custo(6599.99, "blade de alumindur",  LocalDate.of(1972, 9, 13), Departamento.ENGENHARIA, CustoCategoria.MANUTENCAODEBENS);
        //ArrayList<Funcionario> funcionarios;
        Funcionario Alexandre = new Funcionario(1, "Alexandre", Departamento.RH);
        Funcionario Bianca = new Funcionario(2, "Bianca", Departamento.VENDAS);


        SistemaPessoas SistemaP = new SistemaPessoas(new ArrayList<>());
        SistemaP.adicionarFuncionario(Alexandre);
        SistemaP.adicionarFuncionario(Bianca);

    SistemaP.selecionarFuncionario("Bianca");
    SistemaP.selecionarFuncionario("oi");
    SistemaP.printFuncionarios();

    SistemaCustos SistemaC = new SistemaCustos(new ArrayList<>());
    SistemaC.adicionarFuncionario(Balde);
    SistemaC.adicionarFuncionario(TvPequena);
    SistemaC.adicionarFuncionario(TvGrande);
    SistemaC.adicionarFuncionario(Balde);

    SistemaC.acharCustoPorDescricao("tv");
    //SistemaC.acharCustoPorDescricao("pequena");
    //SistemaC.acharCustoPorDescricao("de a");
    //SistemaC.acharCustoPorDescricao("de a");
    SistemaC.acharCustoPorDepartamento(Departamento.ENGENHARIA);
    //SistemaC.acharCustoPorDepartamento(Departamento.RH);


}
        SistemaP.selecionarFuncionario("Bianca");
        SistemaP.printFuncionarios();

    }
}

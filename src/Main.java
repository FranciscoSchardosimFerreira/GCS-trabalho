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


        SistemaPessoas SistemaP = new SistemaPessoas(new ArrayList<>());
        SistemaP.adicionarFuncionario(Alexandre);
        SistemaP.adicionarFuncionario(Bianca);

        SistemaP.selecionarFuncionario("Bianca");
        SistemaP.printFuncionarios();

    }
}
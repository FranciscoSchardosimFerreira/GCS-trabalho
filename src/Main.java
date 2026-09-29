//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import Dados.*;
import java.util.ArrayList;
void main() {

    //ArrayList<Funcionario> funcionarios;
    Funcionario Alexandre = new Funcionario(1, "Alexandre", Departamento.RH);
    Funcionario Bianca = new Funcionario(2, "Bianca", Departamento.VENDAS);


    SistemaClasse Sistema = new SistemaClasse(new ArrayList<>());
    Sistema.adicionarFuncionario(Alexandre);
    Sistema.adicionarFuncionario(Bianca);

    Sistema.selecionarFuncionario("Bianca");
    Sistema.printFuncionarios();

}

import java.util.ArrayList;

public class SistemaClasse {

    private ArrayList<Funcionario> funcionarios;

    private Funcionario funcionarioAtual;

    public SistemaClasse(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void adicionarFuncionario(Funcionario f) {
        funcionarios.add(f);
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void selecionarFuncionario(String nome) {

        for (Funcionario i : funcionarios) {

            if (i.getNome().equals(nome)){
                this.funcionarioAtual = i;
                return;
                
            }


        }
        System.out.println("não encontrado!");
        return;

    }

    public void printFuncionarios() {

        for (Funcionario i : funcionarios) {
            if (i == funcionarioAtual){
                System.out.println(i.getNome() + "(ATUAL) \n");

            }else{

                System.out.println(i.getNome() + "\n");
            }


        }

        return;

    }

    public Funcionario getFuncionarioAtual() {
        return funcionarioAtual;
    }
}
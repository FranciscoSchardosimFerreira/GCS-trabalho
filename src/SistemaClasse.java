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

    public void selecionarFuncionario(Funcionario f) {
        this.funcionarioAtual = f;
    }

    public Funcionario getFuncionarioAtual() {
        return funcionarioAtual;
    }
}
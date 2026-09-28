public class Funcionario {

    private int matricula;
    private String nome;
    private Departamento departamento;

    public Funcionario(int matricula, String nome, Departamento departamento) {
        this.matricula = matricula;
        this.nome = nome;
        this.departamento = departamento;
    }

    public int getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }


    @Override
    public String toString() {
        return "Funcionario{" +
                "matricula=" + matricula +
                ", nome='" + nome + '\'' +
                ", departamento=" + departamento +
                '}';
    }
}

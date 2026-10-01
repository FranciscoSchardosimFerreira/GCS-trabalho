package Dados;
public class Funcionario {

    private int matricula;
    private String nome;
    private Departamento departamento;

    public Funcionario(int matricula, String nome, Departamento departamento) {
        if(matricula <= 0){
            throw new IllegalArgumentException("Matricula deve ser positiva.");
        
        }
        if(nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }

        if(departamento == null){
            throw new IllegalArgumentException("Departamento não pode ser nulo.");
        }
        this.matricula = matricula;
        this.nome = nome.trim();
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
        if(nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        this.nome = nome;
    }

    public void setDepartamento(Departamento departamento) {
        if(departamento == null){
            throw new IllegalArgumentException("Departamento não pode ser nulo.");
        }
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

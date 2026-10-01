package Dados;
public enum Departamento {

    RH("Recursos Humanos"),
    COMPRAS("Compras"),
    VENDAS("Vendas"),
    EXPEDICAO("Expedição"),
    PRODUCAO("Produção"),
    ENGENHARIA("Engenharia");

    private String nome;

    Departamento(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}

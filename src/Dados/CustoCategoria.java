package Dados;
public enum CustoCategoria {

    AQUISICAODEBENS("Aquisição de bens"),
    MANUTENCAODEBENS("Manutenção de bens"),
    OUTROSSERVICOS("Outros serviços");

    private String nome;

    CustoCategoria(String nome) {
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

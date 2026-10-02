package Dados;

import java.util.Comparator;

public enum CriterioOrdenacao {

    DATA("Data", Comparator.comparing(Custo::getData)),
    VALOR("Valor", Comparator.comparing(Custo::getValor)),
    DESCRICAO("Descrição", Comparator.comparing(Custo::getDescricao, String.CASE_INSENSITIVE_ORDER)),
    DEPARTAMENTO("Departamento", Comparator.comparing(c -> c.getDepartamento().getNome())),
    CATEGORIA("Categoria", Comparator.comparing(c -> c.getCusotCategoria().name()));

    private String nome;
    private Comparator<Custo> comparador;

    CriterioOrdenacao(String nome, Comparator<Custo> comparador) {
        this.nome = nome;
        this.comparador = comparador;
    }

    public String getNome() {
        return nome;
    }

    // empates são desfeitos pela data, para a ordem ser sempre a mesma
    public Comparator<Custo> getComparador() {
        return comparador.thenComparing(Custo::getData);
    }

    @Override
    public String toString() {
        return nome;
    }
}

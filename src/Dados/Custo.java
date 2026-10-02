package Dados;
import java.time.LocalDate;
public class Custo implements Comparable<Custo> {

    private Double valor;
    private String descricao;
    private LocalDate  data;
    private Departamento departamento;

    public Custo(Double valor, String descricao, LocalDate data, Departamento departamento, CustoCategoria cusotCategoria) {
        this.valor = validarValor(valor);
        this.descricao = validarDescricao(descricao);
        this.data = validarData(data);
        this.departamento = validarDepartamento(departamento);
        this.cusotCategoria = validarCategoria(cusotCategoria);
    }

    private static Double validarValor(Double valor) {
        if (valor == null || valor.isNaN() || valor.isInfinite()) {
            throw new IllegalArgumentException("O valor do custo é inválido.");
        }
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do custo deve ser maior que zero.");
        }
        return valor;
    }

    private static String validarDescricao(String descricao) {
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("A descrição do custo não pode ser vazia.");
        }
        return descricao.trim();
    }

    private static LocalDate validarData(LocalDate data) {
        if (data == null) {
            throw new IllegalArgumentException("A data do custo é obrigatória.");
        }
        if (data.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data do custo não pode ser futura.");
        }
        return data;
    }

    private static Departamento validarDepartamento(Departamento departamento) {
        if (departamento == null) {
            throw new IllegalArgumentException("O departamento do custo é obrigatório.");
        }
        return departamento;
    }

    private static CustoCategoria validarCategoria(CustoCategoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("A categoria do custo é obrigatória.");
        }
        return categoria;
    }

    public CustoCategoria getCusotCategoria() {
        return cusotCategoria;
    }

    public void setCusotCategoria(CustoCategoria cusotCategoria) {
        this.cusotCategoria = validarCategoria(cusotCategoria);
    }

    private CustoCategoria cusotCategoria;

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = validarDepartamento(departamento);
    }






    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = validarValor(valor);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = validarDescricao(descricao);
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = validarData(data);
    }

    @Override
    public String toString() {
        return "Custo{" +
                "valor=" + valor +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                ", departamento=" + departamento +
                ", cusotCategoria=" + cusotCategoria +
                '}';
    }

    //paraordenarpordata
    @Override
    public int compareTo(Custo next) {
        return this.data.compareTo(next.data);
    }
}

package Dados;
import java.time.LocalDate;
public class Custo implements Comparable<Custo> {

    private Double valor;
    private String descricao;
    private LocalDate  data;
    private Departamento departamento;

    public Custo(Double valor, String descricao, LocalDate data, Departamento departamento, CustoCategoria cusotCategoria) {
        this.valor = valor;
        this.descricao = descricao;
        this.data = data;
        this.departamento = departamento;
        this.cusotCategoria = cusotCategoria;
    }

    public CustoCategoria getCusotCategoria() {
        return cusotCategoria;
    }

    public void setCusotCategoria(CustoCategoria cusotCategoria) {
        this.cusotCategoria = cusotCategoria;
    }

    private CustoCategoria cusotCategoria;

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }






    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
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

package Dados;
import java.time.LocalDate;
public class Custo implements Comparable<Custo> {

    // contador para dar um número único a cada registro, na ordem em que foram cadastrados
    private static int proximoId = 1;

    private final int id;
    private Double valor;
    private String descricao;
    private LocalDate  data;
    private Departamento departamento;
    private CustoCategoria cusotCategoria;
    // funcionário (operador) que registrou o custo
    private final Funcionario funcionario;

    public Custo(Double valor, String descricao, LocalDate data, Departamento departamento,
                 CustoCategoria cusotCategoria, Funcionario funcionario) {
        this.valor = validarValor(valor);
        this.descricao = validarDescricao(descricao);
        this.data = validarData(data);
        this.departamento = validarDepartamento(departamento);
        this.cusotCategoria = validarCategoria(cusotCategoria);
        this.funcionario = validarFuncionario(funcionario);
        this.id = proximoId++;
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

    private static Funcionario validarFuncionario(Funcionario funcionario) {
        if (funcionario == null) {
            throw new IllegalArgumentException("É preciso um funcionário logado para registrar um custo.");
        }
        return funcionario;
    }

    public int getId() {
        return id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public CustoCategoria getCusotCategoria() {
        return cusotCategoria;
    }

    public void setCusotCategoria(CustoCategoria cusotCategoria) {
        this.cusotCategoria = validarCategoria(cusotCategoria);
    }

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
        return String.format("#%-3d %s | %13s | %-19s | %-17s | %s | registrado por %s (%s)",
                id,
                Entrada.formatarData(data),
                Entrada.formatarValor(valor),
                cusotCategoria,
                departamento,
                descricao,
                funcionario.getNome(),
                funcionario.mostrarIniciais());
    }

    // ordena por data; no mesmo dia, o registrado por último é considerado o mais recente
    @Override
    public int compareTo(Custo next) {
        int comparacao = this.data.compareTo(next.data);
        if (comparacao != 0) {
            return comparacao;
        }
        return Integer.compare(this.id, next.id);
    }
}

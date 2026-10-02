package Dados;

import java.time.LocalDate;
import java.time.YearMonth;

// Carrega funcionários e custos de exemplo para facilitar os testes (Requisito Geral 5).
// As datas são calculadas a partir da data de hoje, assim o painel (mês atual e últimos 3 meses)
// sempre tem dados, não importa quando o programa for executado.
public class DadosIniciais {

    private DadosIniciais() {
    }

    public static void carregar(SistemaPessoas pessoas, SistemaCustos custos) {

        Funcionario ana      = novo(pessoas, 101, "Ana Paula Ribeiro",     Departamento.RH);
        Funcionario bruno    = novo(pessoas, 102, "Bruno Henrique Costa",  Departamento.COMPRAS);
        Funcionario carla    = novo(pessoas, 103, "Carla Mendes",          Departamento.VENDAS);
        Funcionario diego    = novo(pessoas, 104, "Diego Fernandes Lima",  Departamento.EXPEDICAO);
        Funcionario eduarda  = novo(pessoas, 105, "Eduarda Souza",         Departamento.ENGENHARIA);
        Funcionario felipe   = novo(pessoas, 106, "Felipe Augusto Rocha",  Departamento.PRODUCAO);
        Funcionario gabriela = novo(pessoas, 107, "Gabriela Martins",      Departamento.VENDAS);
        Funcionario heitor   = novo(pessoas, 108, "Heitor Almeida",        Departamento.COMPRAS);
        Funcionario isabela  = novo(pessoas, 109, "Isabela Nunes Prado",   Departamento.PRODUCAO);
        Funcionario joao     = novo(pessoas, 110, "João Victor Teixeira",  Departamento.ENGENHARIA);

        CustoCategoria aquisicao  = CustoCategoria.AQUISICAODEBENS;
        CustoCategoria manutencao = CustoCategoria.MANUTENCAODEBENS;
        CustoCategoria servicos   = CustoCategoria.OUTROSSERVICOS;

        // ---- 5 meses atrás ----
        custo(custos, 5, 4,  "Aquisição de 10 cadeiras ergonômicas",        6890.00, Departamento.RH,         aquisicao,  ana);
        custo(custos, 5, 12, "Manutenção preventiva da empilhadeira",       1450.00, Departamento.EXPEDICAO,  manutencao, diego);
        custo(custos, 5, 20, "Licença anual de software CAD",              12500.00, Departamento.ENGENHARIA, aquisicao,  eduarda);
        custo(custos, 5, 27, "Serviço de dedetização da fábrica",           980.00, Departamento.PRODUCAO,   servicos,   felipe);

        // ---- 4 meses atrás ----
        custo(custos, 4, 3,  "Aquisição de notebook para vendedor externo", 4299.90, Departamento.VENDAS,     aquisicao,  carla);
        custo(custos, 4, 10, "Conserto do ar-condicionado da sala 2",       750.00, Departamento.COMPRAS,    manutencao, bruno);
        custo(custos, 4, 18, "Treinamento de NR-12 para operadores",       3200.00, Departamento.PRODUCAO,   servicos,   isabela);
        custo(custos, 4, 25, "Frete de amostras para clientes",             640.50, Departamento.VENDAS,     servicos,   gabriela);

        // ---- 3 meses atrás ----
        custo(custos, 3, 5,  "Aquisição de impressora colorida",            980.00, Departamento.VENDAS,     aquisicao,  carla);
        custo(custos, 3, 11, "Troca de pneus do caminhão de entregas",     5600.00, Departamento.EXPEDICAO,  manutencao, diego);
        custo(custos, 3, 19, "Consultoria em pesquisa de clima",           8000.00, Departamento.RH,         servicos,   ana);
        custo(custos, 3, 26, "Calibração de instrumentos de medição",      1870.00, Departamento.ENGENHARIA, manutencao, joao);

        // ---- 2 meses atrás ----
        custo(custos, 2, 2,  "Aquisição de paleteira hidráulica",          2350.00, Departamento.EXPEDICAO,  aquisicao,  heitor);
        custo(custos, 2, 9,  "Manutenção da prensa hidráulica",            7400.00, Departamento.PRODUCAO,   manutencao, felipe);
        custo(custos, 2, 15, "Exames admissionais terceirizados",          1260.00, Departamento.RH,         servicos,   ana);
        custo(custos, 2, 23, "Aquisição de monitores 27 polegadas",        5980.00, Departamento.ENGENHARIA, aquisicao,  eduarda);
        custo(custos, 2, 28, "Recarga de cartuchos da impressora",          320.00, Departamento.COMPRAS,    manutencao, bruno);

        // ---- mês passado ----
        custo(custos, 1, 3,  "Feira comercial - aluguel de estande",      15000.00, Departamento.VENDAS,     servicos,   gabriela);
        custo(custos, 1, 8,  "Aquisição de matéria-prima para protótipo",  4100.00, Departamento.ENGENHARIA, aquisicao,  joao);
        custo(custos, 1, 14, "Manutenção do sistema de esteiras",          3350.00, Departamento.PRODUCAO,   manutencao, isabela);
        custo(custos, 1, 21, "Aquisição de impressora de etiquetas",       1190.00, Departamento.EXPEDICAO,  aquisicao,  diego);
        custo(custos, 1, 27, "Assinatura de plataforma de cotações",        890.00, Departamento.COMPRAS,    servicos,   heitor);

        // ---- mês atual (dia 1 e hoje, para nunca gerar data futura) ----
        LocalDate primeiroDia = YearMonth.now().atDay(1);
        LocalDate hoje = LocalDate.now();
        custos.adicionarCusto(new Custo(2780.00, "Aquisição de uniformes para a produção", primeiroDia,
                Departamento.PRODUCAO, aquisicao, felipe));
        custos.adicionarCusto(new Custo(450.00, "Manutenção do relógio ponto", primeiroDia,
                Departamento.RH, manutencao, ana));
        custos.adicionarCusto(new Custo(1650.00, "Serviço de motoboy para entregas urgentes", hoje,
                Departamento.EXPEDICAO, servicos, diego));
        custos.adicionarCusto(new Custo(3499.00, "Aquisição de tablet para demonstração a clientes", hoje,
                Departamento.VENDAS, aquisicao, carla));

        // começa com o primeiro funcionário selecionado; pode ser trocado pelo menu
        pessoas.setFuncionarioAtual(ana);
    }

    private static Funcionario novo(SistemaPessoas pessoas, int matricula, String nome, Departamento dpt) {
        Funcionario f = new Funcionario(matricula, nome, dpt);
        pessoas.adicionarFuncionario(f);
        return f;
    }

    // mesesAtras = quantos meses antes do atual; dia sempre <= 28 para existir em qualquer mês
    private static void custo(SistemaCustos custos, int mesesAtras, int dia, String descricao, double valor,
                              Departamento dpt, CustoCategoria cat, Funcionario f) {
        LocalDate data = YearMonth.now().minusMonths(mesesAtras).atDay(dia);
        custos.adicionarCusto(new Custo(valor, descricao, data, dpt, cat, f));
    }
}

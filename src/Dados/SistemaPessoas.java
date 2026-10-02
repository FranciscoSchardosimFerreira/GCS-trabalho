package Dados;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemaPessoas {

    private ArrayList<Funcionario> funcionarios;

    private Funcionario funcionarioAtual;

    public SistemaPessoas(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void adicionarFuncionario(Funcionario f) {
        if(f == null){
            throw new IllegalArgumentException("Funcionário nulo.");
        }
        if(existeMatricula(f.getMatricula())){
            throw new IllegalArgumentException("Matrícula já cadastrada!");
        }
        funcionarios.add(f);
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void selecionarFuncionario(String nome) {
        for (Funcionario i : funcionarios) {
            if (i.getNome().equalsIgnoreCase(nome.trim())) {
                this.funcionarioAtual = i;
                return;
            }
        }
        System.out.println("Funcionário não encontrado!");
    }

    // Requisito 1: escolher de uma lista o funcionário que está usando o sistema
    public void selecionarFuncionario(Scanner sc) {
        if (funcionarios.isEmpty()) {
            System.out.println("Não há funcionários cadastrados.");
            return;
        }
        System.out.println("\n===== SELECIONAR OPERADOR =====");
        printFuncionarios();
        int opcao = Entrada.lerInteiro(sc, "Número do funcionário que vai usar o sistema: ", 1, funcionarios.size());
        funcionarioAtual = funcionarios.get(opcao - 1);
        System.out.println("Operador atual: " + funcionarioAtual.getNome()
                + " (" + funcionarioAtual.mostrarIniciais() + ")");
    }

    public void setFuncionarioAtual(Funcionario funcionario) {
        if (funcionario != null && !funcionarios.contains(funcionario)) {
            throw new IllegalArgumentException("Funcionário não cadastrado.");
        }
        this.funcionarioAtual = funcionario;
    }

    public void printFuncionarios() {
        System.out.printf("%-4s %-5s %-28s %-5s %s%n", "Nº", "Matr.", "Nome", "Inic.", "Departamento");
        for (int i = 0; i < funcionarios.size(); i++) {
            Funcionario f = funcionarios.get(i);
            String marcador = (f == funcionarioAtual) ? "  <- ATUAL" : "";
            System.out.printf("%-4s %s%s%n", (i + 1) + ")", f, marcador);
        }
    }

    public Funcionario cadastrarFuncionario(Scanner sc) {
        System.out.println("\n===== CADASTRO DE FUNCIONÁRIO =====");
        int matricula;
        String nome;

        while (true) {
            System.out.print("Matrícula: ");
            try {
                matricula = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Matricula inválida, digite apenas números.");
                continue;
            }
            if (matricula <= 0) {
                System.out.println("A matrícula deve ser um número positivo.");
                continue;
            }
            if (existeMatricula(matricula)) {
                System.out.println("Essa matrícula ja está vinculada a outro funcionário!");
                continue;
            }
            break;
        }

        do {
            System.out.print("Digite o nome do funcionário: ");
            nome = sc.nextLine().trim();
            if (nome.isEmpty()) {
                System.out.println("O nome não pode ser vazio");
            }
        } while (nome.isEmpty());

        Departamento departamento = Entrada.escolher(sc, "Departamentos:", Departamento.values());

        Funcionario funcionario = new Funcionario(matricula, nome, departamento);
        adicionarFuncionario(funcionario);

        System.out.println("O funcionário está cadastrado no sistema!");
        System.out.println(funcionario);
        return funcionario;
    }

    public boolean existeMatricula(int matricula) {
        for (Funcionario f : funcionarios) {
            if (f.getMatricula() == matricula) {
                return true;
            }
        }
        return false;
    }

    public Funcionario getFuncionarioAtual() {
        return funcionarioAtual;
    }

    public void listarDepartamentos() {
        System.out.println("\n===== DEPARTAMENTOS =====");

        Departamento[] departamentos = Departamento.values();

        for (int i = 0; i < departamentos.length; i++) {
            int quantidade = 0;
            for (Funcionario f : funcionarios) {
                if (f.getDepartamento() == departamentos[i]) {
                    quantidade++;
                }
            }
            System.out.printf("%d - %-18s %d funcionário(s)%n", i + 1, departamentos[i].getNome(), quantidade);
        }

        System.out.println("=========================");
    }

}

package Dados;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemaPessoas {

    private Scanner sc;

    private ArrayList<Funcionario> funcionarios;

    private Funcionario funcionarioAtual;

    public SistemaPessoas(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void adicionarFuncionario(Funcionario f) {
        funcionarios.add(f);
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void selecionarFuncionario(String nome) {
        for (Funcionario i : funcionarios) {
            if (i.getNome().equals(nome)) {
                this.funcionarioAtual = i;
                return;
            }
        }
        System.out.println("não encontrado!");
        return;
    }

    public void printFuncionarios() {
        for (Funcionario i : funcionarios) {
            if (i == funcionarioAtual) {
                System.out.println(i.getNome() + "(ATUAL) \n");
            } else {
                System.out.println(i.getNome() + "\n");
            }
        }
        return;
    }

    public void cadastrarFuncionario(Scanner sc) {
        System.out.println("Cadastramento de funcionário");
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

        Departamento[] departamentos = Departamento.values();
        System.out.println("Departamentos: ");
        for (int i = 0; i < departamentos.length; i++) {
            System.out.println((i + 1) + " - " + departamentos[i]);
        }

        Departamento departamento;
        while (true) {
            System.out.println("Escolha o departamento do funcionário:");
            try {
                int opcao = Integer.parseInt(sc.nextLine().trim());
                if (opcao >= 1 && opcao <= departamentos.length) {
                    departamento = departamentos[opcao - 1];
                    break;
                }
            } catch (NumberFormatException e) {
                // cai na mensagem abaixo
            }
            System.out.println("Opção inválida");
        }

        Funcionario funcionario = new Funcionario(matricula, nome, departamento);
        adicionarFuncionario(funcionario);

        System.out.println("O funcionário está cadastrado no sistema!");
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

}
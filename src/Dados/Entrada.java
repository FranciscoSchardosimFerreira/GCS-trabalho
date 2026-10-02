package Dados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;
import java.util.Scanner;

// Métodos de leitura do teclado com validação, para não repetir os mesmos laços em várias classes
public class Entrada {

    public static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    private Entrada() {
    }

    public static String lerTexto(Scanner sc, String mensagem) {
        String texto;
        do {
            System.out.print(mensagem);
            texto = sc.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("O campo não pode ficar vazio.");
            }
        } while (texto.isEmpty());
        return texto;
    }

    public static int lerInteiro(Scanner sc, String mensagem, int minimo, int maximo) {
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = Integer.parseInt(sc.nextLine().trim());
                if (valor >= minimo && valor <= maximo) {
                    return valor;
                }
            } catch (NumberFormatException e) {
                // cai na mensagem abaixo
            }
            System.out.println("Opção inválida. Digite um número entre " + minimo + " e " + maximo + ".");
        }
    }

    // Aceita DD/MM/AAAA (padrão brasileiro) ou AAAA-MM-DD
    public static LocalDate lerData(Scanner sc, String mensagem, boolean permitirFutura) {
        while (true) {
            System.out.print(mensagem);
            String texto = sc.nextLine().trim();
            try {
                LocalDate data = texto.contains("/")
                        ? LocalDate.parse(texto, FORMATO_DATA)
                        : LocalDate.parse(texto);
                if (!permitirFutura && data.isAfter(LocalDate.now())) {
                    System.out.println("A data não pode ser futura.");
                    continue;
                }
                return data;
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Use o formato DD/MM/AAAA.");
            }
        }
    }

    // Mostra as opções numeradas e devolve a escolhida
    public static <T> T escolher(Scanner sc, String titulo, T[] opcoes) {
        System.out.println(titulo);
        for (int i = 0; i < opcoes.length; i++) {
            System.out.println("  " + (i + 1) + " - " + opcoes[i]);
        }
        int escolha = lerInteiro(sc, "Escolha: ", 1, opcoes.length);
        return opcoes[escolha - 1];
    }

    public static boolean confirmar(Scanner sc, String mensagem) {
        while (true) {
            System.out.print(mensagem + " (S/N): ");
            String resposta = sc.nextLine().trim().toUpperCase();
            if (resposta.equals("S")) {
                return true;
            }
            if (resposta.equals("N")) {
                return false;
            }
            System.out.println("Responda S ou N.");
        }
    }

    public static String formatarData(LocalDate data) {
        return data.format(FORMATO_DATA);
    }

    public static String formatarValor(double valor) {
        return String.format(Locale.US, "R$ %,.2f", valor)
                .replace(',', '#').replace('.', ',').replace('#', '.');
    }
}

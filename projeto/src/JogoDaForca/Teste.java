package JogoDaForca;

import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {
        try {
            JogoDaForca jogo = new JogoDaForca();
            jogo.iniciar();
            Scanner scanner = new Scanner(System.in);

            while (!jogo.terminou()) {
                System.out.println("Dica: " + jogo.getDica());
                System.out.println("Palavra: " + jogo.getPalavraAdivinhada());
                System.out.println("Digite uma letra:");
                String letra = scanner.next().toUpperCase();
                
                if (letra.length() == 1 && Character.isLetter(letra.charAt(0))) {
                    // Processa a letra digitada
                    // Exemplo: jogo.processarTentativa(letra);
                } else {
                    System.out.println("Entrada inválida. Digite uma única letra.");
                }
            }

            System.out.println("Fim do jogo!");
            System.out.println("Resultado: " + jogo.getResultado());
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

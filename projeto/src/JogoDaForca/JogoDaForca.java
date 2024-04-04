package JogoDaForca;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class JogoDaForca {
	
	private static final int MAX_PENALIDADES = 6;
	private int numPenalidades = 0;
	private ArrayList<String> palavras = new ArrayList<>();
	private ArrayList<String> dicas = new ArrayList<>();
	private int tamanho;
	private String palavraAdivinhada;
	private int acertos;
	private boolean resultado;
	
	public JogoDaForca() throws Exception{
		//abrir arquivo palavras.txt da pasta interna "/dados" para leitura
		InputStream stream = this.getClass().getResourceAsStream("/dados/palavras.txt");
		if (stream == null)
			throw new Exception("Arquivo de palavras inexistente");
		Scanner arquivo = new Scanner(stream);
		//---Scanner arquivo = new Scanner(new File("c:/palavras.txt")); //abrir arquivo externo do S.O.
	
		// leitura das linhas do arquivo para as respectivas listas
		String linha;
		while (arquivo.hasNext()) {
			linha = arquivo.nextLine().toUpperCase();
			//System.out.println(linha);
			this.palavras.add(linha.split(";")[0]);
			this.dicas.add(linha.split(";")[1]);
		}
		arquivo.close();
		System.out.println(palavras);
		System.out.println(dicas);
	}
	
	public void iniciar() {
		Random rand = new Random();
		int indexSorteado = rand.nextInt(palavras.size());
		String palavraSorteada = palavras.get(indexSorteado);
		System.out.println(palavraSorteada);
	}
	
	public String getDica() {
		return dicas.get(palavras.indexOf(palavraAdivinhada));
	}
	
	public int getTamanho() {
		return tamanho;
	}
	
	public ArrayList<Integer> getOcorrencias(String letra) throws Exception {
		ArrayList<Integer> ocorrencias = new ArrayList<>();
		for (int i = 0; i < palavraAdivinhada.length(); i++) {
			if (palavraAdivinhada.charAt(i) == letra.charAt(0)) {
				ocorrencias.add(i);
			}
		}
		return ocorrencias;
	}
	
	public boolean terminou() {
		return acertos == tamanho || getNumeroPenalidade() >= MAX_PENALIDADES;
	}
	
	public String getPalavraAdivinhada() {
		StringBuilder palavraAdivinhada = new StringBuilder();
		for(int i = 0; i < tamanho; i++) {
			char letra = palavraAdivinhada.charAt(i);
			if (letra != ' ') {
				palavraAdivinhada.append(letra);
	
			} else {
				palavraAdivinhada.append("_ ");
			}
		}
		return palavraAdivinhada.toString();
	}
	
	public int getAcertos() {
		return acertos;
	}
	
	public int getNumeroPenalidade() {
		return numPenalidades;
	}
	
	public String getNomePenalidade() {
		String nomePenalidade = "";
		if (numPenalidades == 1) {
			nomePenalidade = "perdeu primeira perna";
		} else if (numPenalidades == 2) {
			nomePenalidade = "perdeu segunda perna";
		} else if (numPenalidades == 3) {
			nomePenalidade = "perdeu primeiro braço";
		}else if (numPenalidades == 4) {
			nomePenalidade = "perdeu segundo braço";
		} else if (numPenalidades == 5) {
			nomePenalidade = "perdeu tronco";
		} else if (numPenalidades == 6) {
			nomePenalidade = "perdeu cabeça";
		} else {
			nomePenalidade = "sem penalidades";
		}
		return nomePenalidade;
	}
	
	public String getResultado() {
		if (acertos == tamanho) {
	        return "Você ganhou!";
	    } else if (getNumeroPenalidade() >= MAX_PENALIDADES) {
	        return "Você perdeu!";
	    } else {
	        return "Em andamento";
	    }
	}
	
	
	
	/*public static void main(String[] args) {
		System.out.println("asekao");
		System.out.println();
	}*/
	
}


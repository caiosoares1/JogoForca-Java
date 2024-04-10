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
	ArrayList<String> letrasErradas = new ArrayList<>();
	private String palavraSorteada = "";
	private int tamanho;
	private String palavraAdivinhada;
	private StringBuilder palavraAdivinhadaBuilder = new StringBuilder();
	private int acertos = 0;
	private String resultado;
	
	public JogoDaForca() throws Exception{
		InputStream stream = this.getClass().getResourceAsStream("/dados/palavras.txt");
		if (stream == null)
			throw new Exception("Arquivo de palavras inexistente");
		Scanner arquivo = new Scanner(stream);
		String linha;
		while (arquivo.hasNext()) {
			linha = arquivo.nextLine().toUpperCase();
			this.palavras.add(linha.split(";")[0]);
			this.dicas.add(linha.split(";")[1]);
		}
		arquivo.close();
	}
	
	public void iniciar() {
		Random rand = new Random();
		int indexSorteado = rand.nextInt(palavras.size());
		palavraSorteada = palavras.get(indexSorteado);
		tamanho = palavraSorteada.length();
		for (int i = 0; i < tamanho; i++) {
			palavraAdivinhadaBuilder.append("_");
		}
		palavraAdivinhada = palavraAdivinhadaBuilder.toString();
		System.out.println(palavraSorteada);
		System.out.println(palavraAdivinhada);
	}
	
	public String getDica() {
	    if (palavras.contains(palavraSorteada)) {
	        int index = palavras.indexOf(palavraSorteada);
	        return dicas.get(index);
	    } else {
	        return "Palavra sorteada não encontrada na lista de palavras.";
	    }
	}

	
	public int getTamanho() {
		tamanho = palavraSorteada.length();
		return tamanho;
	}
	
	public ArrayList<Integer> getOcorrencias(String letra) throws Exception {
		letra = letra.toUpperCase();
		
	    if (letra == null || letra.isEmpty()) {
	        throw new IllegalArgumentException("A letra fornecida não pode ser nula ou vazia.");
	    }
	    if (letra.length()!=1) {
	    	throw new Exception("Digite apenas uma letra.");
	    }
	    if (palavraAdivinhada.contains(letra)) {
	    	throw new Exception("A letra já foi digitada.");
	    }
	    if (letrasErradas.contains(letra)) {
	        throw new Exception("Você já digitou essa letra.");
	    }
	    if (palavraSorteada == null) {
	        throw new IllegalStateException("A palavra a ser adivinhada não foi definida.");
	    }
	    	

	    ArrayList<Integer> ocorrencias = new ArrayList<>();
	    for (int i = 0; i < palavraSorteada.length(); i++) {
	        if (palavraSorteada.charAt(i) == letra.charAt(0)) {
	            ocorrencias.add(i);
	         
	            palavraAdivinhadaBuilder.setCharAt(i, letra.charAt(0));
	            acertos++; 
	        } 
	    }
	   
	    if (ocorrencias.isEmpty()) {
	    	letrasErradas.add(letra);
	        numPenalidades++;
	        
	        
	    }
	  
	    palavraAdivinhada = palavraAdivinhadaBuilder.toString();
	    return ocorrencias;
	}
	
	public boolean terminou() {
	    return palavraAdivinhada.equals(palavraSorteada) || getNumeroPenalidade() >= MAX_PENALIDADES;
	}

	public String getPalavraAdivinhada() {
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
			resultado = "Você ganhou!";
	    } else if (getNumeroPenalidade() >= MAX_PENALIDADES) {
	    	resultado = "Você perdeu!";
	    } else {
	    	resultado = "Em andamento";
	    }
		return resultado;
	}
}
package JogoDaForca;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AplicacaoGrafica {

	private JFrame frame;
	private JTextField tentativaLetra;
	private JButton button;
	private JLabel Letra;
	private JLabel Dica;
	private JLabel Palavra;
	private JButton AdivinharBtn;
	private JLabel Corpo;
	private JLabel ErroMsg;
	private JLabel JogoStatus;
	private JLabel Penalidade;
	
	private JogoDaForca jogo;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AplicacaoGrafica window = new AplicacaoGrafica();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public AplicacaoGrafica() throws Exception {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tentativaLetra = new JTextField();
		tentativaLetra.setBounds(50, 143, 53, 20);
		frame.getContentPane().add(tentativaLetra);
		tentativaLetra.setColumns(10);
		
		button = new JButton("Iniciar");
		button.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        try {
		            jogo = new JogoDaForca();
		            jogo.iniciar();
		            String dica = jogo.getDica();
		            if (jogo.getResultado().equals("Em andamento")) {
		                JogoStatus.setText(jogo.getResultado());
		            } 
		            Dica.setText("Dica: "+ dica);
		            Palavra.setText("Palavra: " + jogo.getPalavraAdivinhada());
		            ImageIcon newIcon = new ImageIcon(getClass().getResource("/imagens/"+jogo.getNumeroPenalidade()+".png"));
		            Corpo.setIcon(newIcon);
		            ErroMsg.setText("");
		            Penalidade.setText("");
		            JogoStatus.setForeground(new Color(0, 0, 0));
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
		});
		button.setBounds(142, 11, 132, 23);
		frame.getContentPane().add(button);
		
		Letra = new JLabel("Letra:");
		Letra.setFont(new Font("Times New Roman", Font.BOLD, 13));
		Letra.setBounds(10, 145, 46, 17);
		frame.getContentPane().add(Letra);
		
		
		
		Dica = new JLabel("Dica: ");
		Dica.setFont(new Font("Times New Roman", Font.BOLD, 13));
		Dica.setBounds(10, 90, 252, 44);
		frame.getContentPane().add(Dica);
		
		Palavra = new JLabel("Palavra =");
		Palavra.setFont(new Font("Times New Roman", Font.BOLD, 13));
		Palavra.setBounds(10, 207, 235, 32);
		frame.getContentPane().add(Palavra);
		
		AdivinharBtn = new JButton("Adivinhar");
		AdivinharBtn.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        try {
		            ArrayList<Integer> resultado = jogo.getOcorrencias(tentativaLetra.getText());
		            Palavra.setText("Palavra: " + jogo.getPalavraAdivinhada());
		            tentativaLetra.setText("");
		            if (resultado.isEmpty()) {
		                ImageIcon newIcon = new ImageIcon(getClass().getResource("/imagens/"+jogo.getNumeroPenalidade()+".png"));
		                Corpo.setIcon(newIcon);
		                ErroMsg.setText("");
		                Penalidade.setText(jogo.getNomePenalidade());
		            }
		            if(jogo.getResultado().equals("Você perdeu!")) {
		            	JogoStatus.setText(jogo.getResultado());
		            	JogoStatus.setForeground(new Color(255,0,0));
		            }
		            if(jogo.getResultado().equals("Você ganhou!")) {
		            	JogoStatus.setText(jogo.getResultado());
		            	JogoStatus.setForeground(new Color(0,0,255));
		            }
		        } catch (Exception e1) {
		            ErroMsg.setText(e1.getMessage());
		        }
		    }
		});
		AdivinharBtn.setBounds(113, 142, 89, 23);
		frame.getContentPane().add(AdivinharBtn);
		
		Corpo = new JLabel("");
		ImageIcon icon = new ImageIcon(getClass().getResource("/imagens/0.png"));
		Corpo.setIcon(icon);
		Corpo.setFont(new Font("Times New Roman", Font.BOLD, 13));
		Corpo.setBounds(267, 90, 167, 160);
		frame.getContentPane().add(Corpo);
		
		ErroMsg = new JLabel("");
		ErroMsg.setForeground(new Color(255, 0, 0));
		ErroMsg.setBounds(10, 176, 252, 20);
		frame.getContentPane().add(ErroMsg);
		
		JogoStatus = new JLabel("");
		JogoStatus.setForeground(new Color(0, 0, 0));
		JogoStatus.setBounds(152, 45, 122, 20);
		frame.getContentPane().add(JogoStatus);
		
		Penalidade = new JLabel("");
		Penalidade.setBounds(279, 66, 132, 20);
		frame.getContentPane().add(Penalidade);
	}
}
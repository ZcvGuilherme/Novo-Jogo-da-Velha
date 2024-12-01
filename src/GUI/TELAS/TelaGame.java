package GUI.TELAS;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import GAME.STATUS.ButtonState;
import GAME.STATUS.GameStatus;
import GUI.COMPONENTES.CriarComponente;
import GUI.COMPONENTES.BOTOES.Botao;
import GUI.COMPONENTES.BOTOES.BotaoGame;
import GUI.GAMEMODE.GameMode;
import GUI.OBSERVERS.Observer;

public class TelaGame extends TelaGenerica implements Observer{
	private final int size = 3;
	private TelaGenerica telaPrincipalReferencia;
	private JPanel painelPrincipal;
	private List<BotaoGame> botoes = new ArrayList<>();
	private Botao botaoReiniciar;
	private Botao botaoSair;
	private JLabel campoJogadorAtual;
	private GameMode gameMode;
	private String nomePlayerAtual;
	public TelaGame(GameMode gameMode) {
		super("Jogo da Velha", 500, 600, 300, 100, false);
		painelPrincipal = new JPanel();
		configuraTela();
		iniciarComponentes();
		configuraTelaFechamento();
		this.gameMode = gameMode;
		painelPrincipal.revalidate();
		painelPrincipal.repaint();		
	}
	//----------------------------------------------------\\
			//-----------configura tela --------\\
	private void configuraTela() {
		painelPrincipal.setLayout(null);
		painelPrincipal.setBounds(0, 0, 500, 600);
		tela.add(painelPrincipal);
	}
	//-----------------------------------------------------\\
		//-----------configura Saída da tela --------\\
	public void setTelaPrincipal(TelaGenerica telaPrincipal) {
		this.telaPrincipalReferencia = telaPrincipal;
	}
	private void sairTelaPrincipal() {
		if (telaPrincipalReferencia != null) {
			telaPrincipalReferencia.setVisible(true);}
		fechar();
	}
	private void configuraTelaFechamento() {
		tela.addWindowListener(new WindowAdapter() {
			@Override	
			public void windowClosing(WindowEvent e) {
				sairTelaPrincipal();
			}
		});
	}
	//----------------------------------------------------//
		//-----------configura componentes---------\\
	private void iniciarComponentes() {
					//--configura Botoes--\\
		iniciarBotoes();
					//--Criar Barras--\\
		CriarComponente.adicionarBarras(painelPrincipal, size, 100, 100, 5);
		  		    //--Botao Reiniciar--\\
		botaoReiniciar = CriarComponente.criarBotao("Reiniciar", e -> comandoBotaoReiniciar());
		botaoReiniciarConfig();
					//--Botao Sair--\\
		botaoSair = CriarComponente.criarBotao("Sair", e -> comandoBotaoSair());
        BotaoSairConfig();
        			//--CampoJogador--\\
        campoJogadorAtual = CriarComponente.criarLabel("Jogador atual: " + nomePlayerAtual);
        campoJogadorAtualConfig();
	}
	
	private void iniciarBotoes() {
		int buttonSize = 100;
        int posX = 100;
        int posY = 100;
        
        this.botoes = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                BotaoGame botao = new BotaoGame();
                botao.setI(i);
                botao.setJ(j);
                botao.addActionListener(e -> comandoBotaoGame(botao));
                botao.setBounds(posX + j * buttonSize, posY + i * buttonSize, buttonSize, buttonSize);
                botoes.add(botao);
                painelPrincipal.add(botao);
            }
        }
	}
	private BotaoGame acharBotao(int i, int j) {
		for (BotaoGame botao : botoes) {
			if (botao.getI() == i && botao.getJ() == j) {
				return botao;
			}
		}
		return null;
	}
	private void botaoReiniciarConfig() {
		botaoReiniciar.setBounds(50, 480, 150, 50);
        botaoReiniciar.setCorOriginal(Color.GREEN.brighter());
        botaoReiniciar.setHover(botaoReiniciar.getCorOriginal().darker());
        painelPrincipal.add(botaoReiniciar);
	}
	private void BotaoSairConfig() {
		botaoSair.setBounds(300, 480, 150, 50);
        botaoSair.setCorOriginal(Color.yellow.darker());
        botaoSair.setHover(Color.ORANGE.darker());
        painelPrincipal.add(botaoSair);
	}
	
	private void campoJogadorAtualConfig() {
        campoJogadorAtual.setFont(new Font("Arial", Font.BOLD, 24));
        campoJogadorAtual.setBounds(120, 10, 700, 30);
        painelPrincipal.add(campoJogadorAtual);
    }
	
	//----------------------------------------------------//
		//-----------configuração dos botoes---------\\
	public void limparBotoes() {
		for (BotaoGame botao : botoes) {
			botao.limparBotao();
			}
		}
	public void setBotoesClicaveis(boolean bol) {
		for (BotaoGame botao : botoes) {
			botao.setClicavel(bol);
		}
	}
	private void comandoBotaoGame(BotaoGame botao) {
		int i = botao.getI();
		int j = botao.getJ();
		gameMode.fazerJogada(i, j);
	}
	private void comandoBotaoReiniciar() {
		gameMode.resetarGame();
		limparBotoes();
	}
	private void comandoBotaoSair() {
		if (telaPrincipalReferencia != null)
			telaPrincipalReferencia.setVisible(true);
		fechar();
	}
	
	//----------------------------------------------------//
			//-----------configuração Extras---------\\x	
	public void atualizarBotao(GameStatus status) {
       List<ButtonState> estadoBotoes = status.getBotoes();
       for (ButtonState estado : estadoBotoes) {
    	   int i = estado.getI();
    	   int j = estado.getJ();
    	   
    	   BotaoGame botao = acharBotao(i, j);
    	   
    	   if (botao != null && estado.getSimbolo() != ' ') {
    		   botao.setImage(estado.getSimbolo());
    		   botao.setClicavel(!estado.isClicado());
    	   }
       }
    }
	public void atualizaJogadorAtual(String nome) {
        campoJogadorAtual.setText("Jogador atual: " + nome); 
    }
	@Override
	public void update(GameStatus status) {
		atualizarBotao(status);
		atualizaJogadorAtual(status.getJogadorAtual().getNome());
		painelPrincipal.repaint();
	}
	
	
	
}

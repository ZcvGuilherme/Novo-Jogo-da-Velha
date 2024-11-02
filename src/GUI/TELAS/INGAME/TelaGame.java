package GUI.TELAS.INGAME;

import GAME.Game;
import GAME.PLAYERS.Player;
import GUI.COMPONENTES.CriarComponente;
import GUI.COMPONENTES.GameMode;
import GUI.COMPONENTES.PopUp;
import GUI.COMPONENTES.BOTOES.Botao;
import GUI.COMPONENTES.BOTOES.BotaoGame;
import GUI.TELAS.TelaGenerica;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TelaGame extends TelaGenerica{
	
    private Player playerAtual;
    private final int size;
    private boolean statusGame;
	private TelaGenerica telaPrincipalReferencia;
    private PopUp popUp;
    private JLabel campoJogadorAtual;
    private Botao botaoSair;
    private Botao botaoReiniciar;
    private BotaoGame[][] botoesGame;
    private JPanel painelPrincipal;
    private GameMode gameMode;
	public TelaGame(GameMode gameMode) {
		super(gameMode.getNomeTela(), 500, 600, 300, 100, false);
		this.gameMode = gameMode;
		this.size = gameMode.getSize();

		this.playerAtual = gameMode.getCurrentPlayer();
        iniciarComponentes(size);
        tela.setVisible(true);
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        configuraFechamentoTela();
        painelPrincipal.revalidate();
        painelPrincipal.repaint();
	}
	public void verificarStatusGame() {
        this.statusGame = isGameOver();
    }
	private boolean isGameOver() {
		return gameMode.isDraw() || gameMode.isWin();
		
	}
    public void setTelaPrincipal(TelaGenerica telaPrincipal) {
        this.telaPrincipalReferencia = telaPrincipal;
    }

    public boolean getStatusGame() {
        return statusGame;
    }

    private void sairParaTelaPrincipal() {
        if (telaPrincipalReferencia != null) {
            telaPrincipalReferencia.setVisible(true);
        }
        fechar(); 
    }

    private void verificarVitoria() {
        if (gameMode.isWin()) {
            popUp = new PopUp("VITÓRIA", "VENCEDOR: " + playerAtual.getNome());
            popUp.mostrar();
        } else if (gameMode.isDraw()) {
            popUp = new PopUp("EMPATE", "  EMPATOU");
            popUp.mostrar();
        }
    }

	public void setBotoesClicavel(boolean clicavel){
		for (int i = 0 ; i < botoesGame.length ; i++){
			for (int j = 0; j < botoesGame.length; j++){
				if (botoesGame[i][j].foiClicado())
					botoesGame[i][j].setClicavel(clicavel);
			}
		}
	}
	
    private void ativarBotao(BotaoGame botao, int i, int j) {
        if (botao.getClicavel() && !isGameOver()) {
            gameMode.play(i, j);
			botao.setFoiClicado(true);
            botao.setImage(playerAtual.getsymbol());
            botao.setClicavel(false);
            if (!isGameOver()) {
                changePlayer();
                campoJogadorAtual.setText("Jogador atual: " + playerAtual.getNome());
                setCampoColor();
            }
        }
        verificarVitoria();
    }
    private void changePlayer() {
        playerAtual = gameMode.getCurrentPlayer();
    }

    private void restartGame() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                botoesGame[i][j].limparBotao();
            }
        }
        gameMode.resetGame();
        changePlayer();
    }

    private void configuraFechamentoTela() {
        tela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                sairParaTelaPrincipal();
                fechar();
            }
        });
    }

    private void iniciarComponentes(int size) {
        painelPrincipal = new JPanel(); 
        painelPrincipalConfig();
        jogadorAtualConfig();
        
        botoesGame = CriarComponente.criarBotoes(size, painelPrincipal, e -> {
            BotaoGame botaoClicado = (BotaoGame) e.getSource();
            ativarBotao(botaoClicado, botaoClicado.getI(), botaoClicado.getJ());
        });
        
        botaoReiniciar = CriarComponente.criarBotao("Reiniciar", e -> restartGame());
        botaoReiniciarConfig();
        
        botaoSair = CriarComponente.criarBotao("Sair", e -> sairParaTelaPrincipal());
        botaoSairConfig();
        
        CriarComponente.adicionarBarras(painelPrincipal, size, 100, 100, 5);
        
        painelPrincipal.add(campoJogadorAtual);
        tela.add(painelPrincipal);
    }

    private void painelPrincipalConfig() {
        painelPrincipal.setLayout(null);
        painelPrincipal.setBounds(0, 0, 500, 600);
    }

    private void jogadorAtualConfig() {
        campoJogadorAtual = CriarComponente.criarLabel("Jogador atual: " + playerAtual.getNome());
        campoJogadorAtual.setFont(new Font("Arial", Font.BOLD, 24));
        setCampoColor();
        campoJogadorAtual.setBounds(120, 10, 700, 30);
    }

    private void botaoReiniciarConfig() {
        botaoReiniciar.setBounds(50, 480, 150, 50);
        botaoReiniciar.setCorOriginal(Color.GREEN.brighter());
        botaoReiniciar.setHover(botaoReiniciar.getCorOriginal().darker());
        painelPrincipal.add(botaoReiniciar);
    }

    private void botaoSairConfig() {
        botaoSair.setBounds(300, 480, 150, 50);
        botaoSair.setCorOriginal(Color.yellow.darker());
        botaoSair.setHover(Color.ORANGE.darker());
        painelPrincipal.add(botaoSair);
    }

    private void setCampoColor() {
    	campoJogadorAtual.setForeground((playerAtual.getsymbol() == 'X'? Color.RED: Color.BLUE));
        
    }

}

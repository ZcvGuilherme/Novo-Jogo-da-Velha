package GUI.TELAS.INGAME;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GAME.Game;
import GAME.PLAYERS.Player;
import GUI.COMPONENTES.Botao;
import GUI.COMPONENTES.BotaoGame;
import GUI.COMPONENTES.CriarComponente;
import GUI.COMPONENTES.PopUp;
import GUI.TELAS.TelaGenerica;

public class TelaGame extends TelaGenerica {
    private TelaGenerica telaPrincipalReferencia;
    private Player playerAtual;
    private final int size;
    private final Game game;
    private final Player player1;
    private final Player player2;
    private final List<Player> listaPlayer = new ArrayList<>();
    private boolean statusGame;
    private PopUp popUp;

    private JLabel campoJogadorAtual;
    private Botao botaoSair;
    private Botao botaoReiniciar;
    private BotaoGame[][] botoesGame;
    private JPanel painelPrincipal;

    public TelaGame(int size, String nomePlayer1, String nomePlayer2) {
        super("Jogo da Velha", 500, 600, 300, 100, false);
        this.size = size;
        player1 = new Player(nomePlayer1, 'X');
        player2 = new Player(nomePlayer2, 'O');
        listaPlayer.add(player1);
        listaPlayer.add(player2);
        game = new Game(listaPlayer, size);
        
        playerAtual = game.getCurrentPlayer();
        iniciarComponentes(size);
        tela.setVisible(true);
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        configuraFechamentoTela();
        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }
    
    public void verificarStatusGame() {
        this.statusGame = game.isGameOver();
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
        if (game.isWin()) {
            popUp = new PopUp("VITÓRIA", "VENCEDOR: " + playerAtual.getNome());
            popUp.mostrar();
        } else if (game.isDraw()) {
            popUp = new PopUp("EMPATE", "  EMPATOU");
            popUp.mostrar();
        }
    }

    private void ativarBotao(BotaoGame botao, int i, int j) {
        if (botao.getClicavel() && !game.isGameOver()) {
            game.play(i, j);
            botao.setImage(playerAtual.getsymbol());
            botao.setClicavel(false);
            if (!game.isGameOver()) {
                changePlayer();
                campoJogadorAtual.setText("Jogador atual: " + playerAtual.getNome());
                setCampoColor();
            }
        }
        verificarVitoria();
    }

    private void changePlayer() {
        playerAtual = game.getCurrentPlayer();
    }

    private void restartGame() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                botoesGame[i][j].limparBotao();
            }
        }
        game.resetGame();
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

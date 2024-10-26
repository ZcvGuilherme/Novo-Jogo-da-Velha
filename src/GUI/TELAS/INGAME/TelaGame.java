package GUI.TELAS.INGAME;

import GAME.Game;
import GAME.PLAYERS.Player;
import GUI.COMPONENTES.Botao;
import GUI.COMPONENTES.BotaoGame;
import GUI.COMPONENTES.CriarComponente;
import GUI.COMPONENTES.PopUp;
import GUI.TELAS.TelaGenerica;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class TelaGame extends TelaGenerica{
    private TelaGenerica telaPrincipalReferencia;
    private JLabel campoJogadorAtual;
    private char playerAtual;
    private String nomePlayerAtual;
    private Botao botaoSair;
    private Botao botaoReiniciar;
    private BotaoGame[][] botoesGame;
    private final int size;
    private JPanel painelPrincipal;
    private final Game game;
    private final Player player1;
    private final Player player2;
    private final List<Player> listaPlayer = new ArrayList<>();
    private boolean statusGame;
    private PopUp popUp;

    public TelaGame(int size, String nomePlayer1, String nomePlayer2){
        super("Jogo da Velha", 500, 600, 300, 100, false);
        this.size = size;
        
        player1 = new Player(1, nomePlayer1, 'X');
        player2 = new Player(2, nomePlayer2, 'O');
        listaPlayer.add(player1);
        listaPlayer.add(player2);
        game = new Game(listaPlayer, size);
        this.nomePlayerAtual = game.getCurrentPlayer().getNome();
        playerAtual = game.getCurrentPlayer().getsymbol();
        iniciarComponentes(size);
        tela.setVisible(true);
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        configuraFechamentoTela();
        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }
    public boolean getStatusGame(){
        return statusGame;
    }
    public void changeStatusGame(){
        this.statusGame = game.isGameOver();
    }
    public void setTelaPrincipal(TelaGenerica telaPrincipal){
        this.telaPrincipalReferencia = telaPrincipal;
    }
    private void configuraFechamentoTela(){
        tela.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                sairParaTelaPrincipal();
                fechar();
            }
        });
    }
    private void sairParaTelaPrincipal() {
        if (telaPrincipalReferencia != null) {
            telaPrincipalReferencia.setVisible(true);
        }
        fechar(); 
    }
    
    private void iniciarComponentes(int size){
        painelPrincipal = new JPanel(); 
        painelPrincipalConfig();
        jogadorAtualConfig();
        //------------------------------------//
        botoesGame = CriarComponente.criarBotoes(size, painelPrincipal, e -> {
            BotaoGame botaoClicado = (BotaoGame) e.getSource();
            ativarBotao(botaoClicado, botaoClicado.getI(), botaoClicado.getJ());
        });
        //------------------------------------//
        botaoReiniciar = CriarComponente.criarBotao("Reiniciar", e -> restartGame());
        botaoReiniciarConfig();
        //------------------------------------//
        botaoSair = CriarComponente.criarBotao("Sair", e -> sairParaTelaPrincipal());
        BotaoSairConfig();
        //------------------------------------//
        CriarComponente.adicionarBarras(painelPrincipal, size, 100, 100, 5);
        //------------------------------------//
        painelPrincipal.add(campoJogadorAtual);
        tela.add(painelPrincipal);
    }
    private void restartGame(){
        for (int i = 0; i <size; i++){
            for (int j = 0; j < size; j++){
                botoesGame[i][j].limparBotao();
            }
        }
        game.resetGame();
    }
    private void botaoReiniciarConfig(){
        botaoReiniciar.setBounds(50, 480, 150, 50);
        botaoReiniciar.setCorOriginal(Color.GREEN.brighter());
        botaoReiniciar.setHover(botaoReiniciar.getCorOriginal().darker());
        painelPrincipal.add(botaoReiniciar);
    }
    private void BotaoSairConfig(){
        botaoSair.setBounds(300, 480, 150, 50);
        botaoSair.setCorOriginal(Color.yellow.darker());
        botaoSair.setHover(Color.ORANGE.darker());
        painelPrincipal.add(botaoSair);
    }
    private void painelPrincipalConfig(){
        painelPrincipal.setLayout(null);
        painelPrincipal.setBounds(0, 0, 500,600);
    }
    private void jogadorAtualConfig() {
        campoJogadorAtual = CriarComponente.criarLabel("Jogador atual: " + nomePlayerAtual);
        campoJogadorAtual.setFont(new Font("Arial", Font.BOLD, 24));
        setCampoColor();
        campoJogadorAtual.setBounds(120, 10, 700, 30);
    }
    private void ativarBotao(BotaoGame botao, int i, int j){
        if (game.isDraw()){
            popUp = new PopUp("EMPATE", "EMPATOU");
            popUp.mostrar();
        } else if (game.isWin()){
            popUp = new PopUp("VITÓRIA", "VENCEDOR: " + nomePlayerAtual);
            popUp.mostrar();
        } else if (botao.getClicavel() && !game.isDraw() && !game.isWin()){
            if (game.isDraw()){
                popUp = new PopUp("EMPATE", "EMPATOU");
                popUp.mostrar();
            } else if (game.isWin()){
                popUp = new PopUp("VITÓRIA", "VENCEDOR: " + nomePlayerAtual);
                popUp.mostrar();
            }
            game.play(i, j);
            changePlayerName();
            botao.setImage(playerAtual);
            botao.setClicavel(false);
            campoJogadorAtual.setText("Jogador Atual: " + nomePlayerAtual);
            playerAtual = game.getCurrentPlayer().getsymbol();
            setCampoColor();
            
        } 
    } 
    
    private void changePlayerName(){
        this.nomePlayerAtual = game.getCurrentPlayer().getNome();
    }
    private void setCampoColor(){
        if (playerAtual == 'X'){
            campoJogadorAtual.setForeground(Color.RED);
        } else {
            campoJogadorAtual.setForeground(Color.BLUE);
        }
    }

}

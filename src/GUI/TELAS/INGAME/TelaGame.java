package GUI.TELAS.INGAME;

import GAME.Game;
import GUI.COMPONENTES.Botao;
import GUI.COMPONENTES.BotaoGame;
import GUI.COMPONENTES.CriarComponente;
import GUI.TELAS.OUTGAME.TelaGenerica;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class TelaGame extends TelaGenerica{
    private TelaGenerica telaPrincipalReferencia;
    private JLabel campoJogadorAtual;
    private char playerAtual;
    private final String nomePlayer1;
    private final String nomePlayer2;
    private String nomePlayerAtual;
    private Botao botaoSair;
    private Botao botaoReiniciar;
    private BotaoGame[][] botoesGame;
    private final int size;
    private JPanel painelPrincipal;
    private Game game;

    public TelaGame(int size, String nomePlayer1, String nomePlayer2, Game game){
        super("Jogo da Velha", 500, 600, 300, 100, false);
        this.size = size;
        this.game = game;
        this.nomePlayer1 = nomePlayer1;
        this.nomePlayer2 = nomePlayer2;
        this.nomePlayerAtual = nomePlayer1;
        playerAtual = 'X';
        iniciarComponentes(size);
        tela.setVisible(true);
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        configuraFechamentoTela();
        painelPrincipal.revalidate();
        painelPrincipal.repaint();
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
        if (botao.getClicavel() && game.play(i, j)){
            botao.setImage(playerAtual);
            botao.setClicavel(false);
            changePlayerName();
            campoJogadorAtual.setText("Jogador Atual: " + nomePlayerAtual);
            playerAtual = (playerAtual == 'X') ? 'O' : 'X';
            setCampoColor();
        }
    }
    private void changePlayerName(){
        nomePlayerAtual = (nomePlayerAtual.equals(nomePlayer1)) ? nomePlayer2
: nomePlayer1;
    }
    private void setCampoColor(){
        if (playerAtual == 'X'){
            campoJogadorAtual.setForeground(Color.RED);
        } else {
            campoJogadorAtual.setForeground(Color.BLUE);
        }
    }

}

package GUI.TELAS.OUTGAME;

import GUI.COMPONENTES.CriarComponente;
import GUI.GAMEMODE.GameMode;
import GUI.GAMEMODE.GameMode1v1;
import GUI.GAMEMODE.GameModeBot;
import GUI.TELAS.TelaGame;
import GUI.TELAS.TelaGenerica;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;

public class TelaPrincipal extends TelaGenerica{
    private JLabel campo;
    private JButton vsPC;
    private JButton vsPlayer;
    private JButton vsPlayerNet;
    public TelaPrincipal(String nomePlayer){
        super("Jogo da Velha", 400, 600, 300, 100, false);
        
        Font labelFont = new Font("Arial", Font.BOLD, 24);
        Font buttonFont = new Font("Arial", Font.BOLD, 24);

        campo = CriarComponente.criarLabel("Jogo da Velha");
        vsPC = CriarComponente.criarBotao("VS PC", e -> comando1(nomePlayer));
        vsPlayer = CriarComponente.criarBotao("VS PLAYER", e -> comando2(nomePlayer));
        vsPlayerNet = CriarComponente.criarBotao("VS ONLINE", e -> comando3(nomePlayer));

        campo.setBounds(120, 10, 300, 30);
        vsPC.setBounds(50, 60, 300, 70);
        vsPlayer.setBounds(50, 150, 300, 70);
        vsPlayerNet.setBounds(50, 240, 300, 70);

        campo.setFont(labelFont);
        vsPC.setFont(buttonFont);
        vsPlayer.setFont(buttonFont);
        vsPlayerNet.setFont(buttonFont);

        tela.add(campo);
        tela.add(vsPC);
        tela.add(vsPlayer);
        tela.add(vsPlayerNet);
    }

    private void comando1(String NomePlayer1){
    	GameMode gamemode = new GameModeBot(NomePlayer1);
    	TelaGame telaGame = new TelaGame(gamemode);
    	gamemode.addObserver(telaGame);
        telaGame.mostrar();
        telaGame.setTelaPrincipal(this);
        tela.setVisible(false);  
    }
    private void comando2(String NomePlayer1){
    	GameMode gamemode = new GameMode1v1(NomePlayer1);
    	TelaGame telaGame = new TelaGame(gamemode);
    	gamemode.addObserver(telaGame);
        telaGame.mostrar();    
        telaGame.setTelaPrincipal(this);
        tela.setVisible(false);  
    }
    private void comando3(String NomePlayer1){

    }

}

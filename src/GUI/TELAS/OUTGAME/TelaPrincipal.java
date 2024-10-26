package GUI.TELAS.OUTGAME;

import GAME.Game;
import GAME.Game;
import GUI.COMPONENTES.CriarComponente;
import GUI.TELAS.INGAME.TelaGame;
import GUI.TELAS.INGAME.VSLOCAL.TelaGame1v1;
import GUI.TELAS.Iniciar;
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
    }
    private void comando2(String NomePlayer1){
        tela.setVisible(false);
        TelaGame telaJogo = Iniciar.TelaJogo1v1(NomePlayer1);
        telaJogo.setTelaPrincipal(this);
        
        
    }
    private void comando3(String NomePlayer1){

    }

}

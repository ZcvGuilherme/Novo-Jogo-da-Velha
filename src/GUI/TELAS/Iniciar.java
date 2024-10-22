package GUI.TELAS;

import GAME.Game;
import GUI.TELAS.INGAME.TelaGame;
import GUI.TELAS.INGAME.VSLOCAL.TelaGame1v1;
import GUI.TELAS.OUTGAME.AddPlayer;
import GUI.TELAS.OUTGAME.TelaPrincipal;

public class Iniciar {
    public static void TelaAddPlayer(){
        AddPlayer mainTela = new AddPlayer();
        mainTela.mostrar();
    }
    public static void TelaPrincipal(String nomePlayer){
        TelaPrincipal tela = new TelaPrincipal(nomePlayer);
        tela.mostrar();
    }
    public static TelaGame TelaJogo1v1(String nomePlayer1, Game game){
        TelaGame tela = new TelaGame1v1(nomePlayer1, game);
        tela.mostrar();
        return tela;
    }
}

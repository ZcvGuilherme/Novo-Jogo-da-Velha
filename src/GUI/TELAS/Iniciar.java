package GUI.TELAS;
import GUI.COMPONENTES.GameMode;
import GUI.TELAS.INGAME.TelaGame;
import GUI.TELAS.INGAME.TelaGame;
import GUI.TELAS.INGAME.VSLOCAL.GameMode1v1;
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
    public static void TelaGame(GameMode gameMode) {
    	TelaGame tela = new TelaGame(gameMode);
    }
}

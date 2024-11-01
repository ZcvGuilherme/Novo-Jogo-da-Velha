package GUI.TELAS;
import GUI.COMPONENTES.GameMode;
import GUI.TELAS.INGAME.NewTelaGame;
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
    public static void TelaJogo() {
    	TelaGame tela = new TelaGame(3, "player1", "player2");
    	tela.mostrar();
    }
    public static void TelaGame(GameMode gameMode) {
    	NewTelaGame tela = new NewTelaGame(gameMode);
    }
}

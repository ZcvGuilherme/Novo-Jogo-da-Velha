package GUI.TELAS;

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

}

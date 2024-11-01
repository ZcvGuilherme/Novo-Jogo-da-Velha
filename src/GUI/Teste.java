package GUI;


import GUI.TELAS.INGAME.NewTelaGame;
import GUI.TELAS.INGAME.VSLOCAL.GameMode1v1;
import GUI.TELAS.INGAME.VSONLINE.GameModeVsPC;

public class Teste {
    public static void main(String[] args) {
        NewTelaGame tela = new NewTelaGame(new GameModeVsPC("Guilherme"));
        tela.mostrar();
    }
}

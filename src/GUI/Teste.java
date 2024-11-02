package GUI;


import GUI.TELAS.INGAME.TelaGame;
import GUI.TELAS.INGAME.VSLOCAL.GameMode1v1;
import GUI.TELAS.INGAME.VSONLINE.GameModeVsPC;

public class Teste {
    public static void main(String[] args) {
        TelaGame tela = new TelaGame(new GameMode1v1("Guilherme"));
        tela.mostrar();
    }
}

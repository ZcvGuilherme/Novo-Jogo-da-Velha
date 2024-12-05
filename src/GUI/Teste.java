package GUI;
import GUI.GAMEMODE.GameMode;
import GUI.GAMEMODE.GameMode1v1;
import GUI.GAMEMODE.GameModeBot;
import GUI.GAMEMODE.ONLINE.GAMEMODE.GameModeOnline;
import GUI.GAMEMODE.ONLINE.SELECAO.Menu;
import GUI.TELAS.TelaGame;

public class Teste {
    public static void main(String[] args) {
    	GameMode gamemode = new GameMode1v1("Jogador");
        TelaGame tela = new TelaGame(gamemode);
        gamemode.addObserver(tela);
        tela.mostrar();
    }
}

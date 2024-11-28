package GUI;
import GUI.GAMEMODE.GameMode1v1;
import GUI.GAMEMODE.GameModeBot;
import GUI.GAMEMODE.GameModeOnline;
import GUI.GAMEMODE.ONLINE.SELECAO.Menu;
import GUI.TELAS.TelaGame;

public class Teste {
    public static void main(String[] args) {
        TelaGame tela = new TelaGame(new GameMode1v1("Nome"));
       
        tela.mostrar();
    }
}

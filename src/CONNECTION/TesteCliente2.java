package CONNECTION;

import GUI.GAMEMODE.GameMode;
import GUI.GAMEMODE.ONLINE.GAMEMODE.ClientPlayer;
import GUI.TELAS.TelaGame;

public class TesteCliente2 {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Iniciando Client ");
	    Client client = new Client();
	    GameMode gamemode = new ClientPlayer("Jao");
	    TelaGame tela = new TelaGame(gamemode);
	    tela.mostrar();
	    gamemode.addObserver(tela);
	    gamemode.addObserver(client);
	    client.addObserver((ClientPlayer) gamemode);

	    client.connect("localhost", 12345);
	    client.waitUpdate();
	}
}

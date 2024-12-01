package CONNECTION;

import GUI.GAMEMODE.GameMode;
import GUI.GAMEMODE.GameModeOnline;
import GUI.TELAS.TelaGame;

public class TesteCliente {
	public static void main(String[] args) throws InterruptedException {
		Client client = new Client();
		GameMode gamemode = new GameModeOnline();
		TelaGame tela = new TelaGame(gamemode);
		
		tela.mostrar();
		gamemode.addObserver(tela);
		gamemode.addObserver(client);
		client.addObserver((GameModeOnline)gamemode);
		
		client.connect("localhost", 12345);		
		while (true) {
			client.waitUpdate();
			Thread.sleep(1000);
		}
	}
}

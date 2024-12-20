package CONNECTION.TESTS;

import CONNECTION.CLIENT.Client;
import GUI.GAMEMODE.GameMode;
import GUI.GAMEMODE.ONLINE.GAMEMODE.HostPlayer;
import GUI.TELAS.TelaGame;
public class TesteCliente {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Iniciando Host");
	    Client client = new Client();
	    GameMode gamemode = new HostPlayer("Guilherme");
	    TelaGame tela = new TelaGame(gamemode);
	    tela.mostrar();
	    gamemode.addObserver(tela);
	    gamemode.addObserver(client);
	    client.addObserver((HostPlayer) gamemode);
	    client.connect("localhost", 12345);
	    client.waitUpdate();
	}
	
}

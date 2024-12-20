package CONNECTION.TESTS;

import CONNECTION.SERVER.GameServer;

public class TesteServidor {
	public static void main(String[] args) {
			GameServer server = new GameServer();
			server.startServer();
	}
}

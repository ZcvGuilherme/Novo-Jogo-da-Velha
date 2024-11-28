package GAME.PLAYERS;

import java.util.Random;

import GAME.Game;

public class BotPlayer extends Player {
	private Random random = new Random();
	public BotPlayer(String nome, char symbol) {
		super(nome, symbol);
	}
	
	private void randomPlay(Game game) {
		int boardSize = game.getBoardSize();
		this.i = random.nextInt(boardSize);
		this.j = random.nextInt(boardSize);
	}
	@Override
	public boolean makeMove(Game game) {
		boolean moveMade;
		do {
			randomPlay(game);
			moveMade = game.play(i, j);
			System.out.println("Jogando em: " + i + j);
		} while (!moveMade);
		return moveMade;
	}

}

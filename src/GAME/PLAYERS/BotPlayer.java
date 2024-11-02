package GAME.PLAYERS;

import java.util.Random;

import GAME.Game;

public class BotPlayer extends Player{
	private Random random = new Random();
	public BotPlayer(String nome, char symbol) {
		super(nome, symbol);
	}

	@Override
	public boolean makeMove(Game game) {
		int boardSize = game.getBoardSize();
		int i;
		int j;
		boolean moveMade;
		
		do {
			i = random.nextInt(boardSize);
			j = random.nextInt(boardSize);
			moveMade = game.play(i, j);
			System.out.println("Jogando em: " + i + j);
		} while (!moveMade);
		return moveMade;
	}

}

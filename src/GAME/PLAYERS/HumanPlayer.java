package GAME.PLAYERS;

import GAME.Game;

public class HumanPlayer extends Player {
	
	public HumanPlayer(String nome, char symbol) {
		super(nome, symbol);
	}
	
	public void setMove(int i, int j) {
		this.i = i;
		this.j = j;
	}
	@Override
	public boolean makeMove(Game game) {
		return game.play(i, j);
	}
	
}

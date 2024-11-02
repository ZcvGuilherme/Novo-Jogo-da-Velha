package GAME.PLAYERS;

import GAME.Game;

public class HumanPlayer extends Player{
	private int posI;
	private int posJ;
	public HumanPlayer(String nome, char symbol) {
		super(nome, symbol);
	}
	
	public void setMove(int i, int j) {
		this.posI = i;
		this.posJ = j;
	}
	@Override
	public boolean makeMove(Game game) {
		return game.play(posI, posJ);
	}
	
}

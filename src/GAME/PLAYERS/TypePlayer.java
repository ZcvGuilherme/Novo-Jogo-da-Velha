package GAME.PLAYERS;

import GAME.Game;

public interface TypePlayer  {
	boolean makeMove(Game game);

	char getsymbol();

	String getNome();

}

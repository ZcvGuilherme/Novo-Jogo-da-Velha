package GUI.COMPONENTES;

import GAME.Game;
import GAME.PLAYERS.Player;

public interface GameMode {
	boolean getIsTurn();
	String getNomeTela();
	int getSize();
	void play(int i, int j);
	boolean isDraw();
	boolean isWin();
	Player getCurrentPlayer();
	void resetGame();
	
}

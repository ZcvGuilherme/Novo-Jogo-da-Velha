package GUI.TELAS.INGAME.VSLOCAL;
import java.util.ArrayList;
import java.util.List;

import GAME.Game;
import GAME.PLAYERS.Player;
import GUI.COMPONENTES.GameMode;
public class GameMode1v1 implements GameMode{
    private Game game;
    private int size;
    private String nomeTela;
    private Player player1;
    private Player player2;
    private List<Player> players = new ArrayList<>();
    public GameMode1v1(){
        super();
        this.size = 3;
        player1 = new Player("Player1", 'X');
        player2 = new Player("Player2", 'O');
        players.add(player1);
        players.add(player2);
        this.game = new Game(players, size);
        this.nomeTela = "Player vs Player";
    } 

    public String getNomeTela() {
        return nomeTela;
    }
  
    public Game getGame() {
        return game;
    }

    public int getSize() {
    	return size;
    }
    public void play(int i, int j) {
    	game.play(i, j);
    }

	@Override
	public boolean isDraw() {
		return game.isDraw();
	}

	@Override
	public boolean isWin() {
		return game.isWin();
	}

	@Override
	public Player getCurrentPlayer() {
		return game.getCurrentPlayer();
	}

	@Override
	public void resetGame() {
		game.resetGame();
	}
	public boolean getIsTurn() {
		return true;
	}
}

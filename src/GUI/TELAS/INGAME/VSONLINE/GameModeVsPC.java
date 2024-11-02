package GUI.TELAS.INGAME.VSONLINE;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import GAME.Game;
import GAME.PLAYERS.BotPlayer;
import GAME.PLAYERS.HumanPlayer;
import GAME.PLAYERS.Player;
import GUI.COMPONENTES.GameMode;

public class GameModeVsPC implements GameMode{
	private Game game;
    private int size;
    private String nomeTela;
    private HumanPlayer player1;
    private BotPlayer player2;
    private List<Player> players = new ArrayList<>();

    
	public GameModeVsPC(String nomePlayer) {
		super();
		this.size = 3;
        player1 = new HumanPlayer(nomePlayer, 'X');
        player2 = new BotPlayer("Bot", 'O');
        players.add(player1);
        players.add(player2);
        this.game = new Game(players, size);
        this.nomeTela = "Player vs Bot";
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
	
}

package GUI.TELAS.INGAME.VSONLINE;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import GAME.Game;
import GAME.PLAYERS.Player;
import GUI.COMPONENTES.GameMode;

public class GameModeVsPC implements GameMode{
	private Game game;
    private int size;
    private String nomeTela;
    private Player player1;
    private Player player2;
    private List<Player> players = new ArrayList<>();
    private boolean isTurno;
	public GameModeVsPC(String nomePlayer) {
		super();
		this.size = 3;
        player1 = new Player(nomePlayer, 'X');
        player2 = new Player("Bot", 'O');
        players.add(player1);
        players.add(player2);
        this.game = new Game(players, size);
        this.nomeTela = "Player vs Bot";
        this.isTurno = (game.getCurrentPlayer() == player1);
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
    	try {
			Thread.sleep(500);
			botPlay();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
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
		return isTurno;
	}
	
	private void botPlay() {
		Random random = new Random();
		int posI;
		int posJ;
		boolean jogada;
		do {
			posI = random.nextInt(size);
			posJ = random.nextInt(size);
			jogada = game.play(posI, posJ);
		} while(!jogada);
	}
}

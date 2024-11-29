package GUI.GAMEMODE;

import GAME.PLAYERS.BotPlayer;
import GAME.PLAYERS.HumanPlayer;
import GAME.STATUS.GameStatus;
import GUI.COMPONENTES.PopUp;


public class GameModeBot extends GameMode{
	private static BotPlayer player2 = new BotPlayer("Player 2", 'O');
	//o que significa uma variavel static?
	private static HumanPlayer player1 = new HumanPlayer("Player 1", 'X');
	public GameModeBot(String nomePlayer1) {
		super(player1, player2);
		player1.setNome(nomePlayer1);
		notifyObservers();
		}

	@Override
	public void fazerJogada(int i, int j) {
		boolean jogadaValida;
		if (game.getCurrentPlayer().equals(player1)) {
			jogadaValida = game.play(i, j);
			if (jogadaValida) {
				notifyObservers();
				verificarStatus();
				fazerJogada(0, 0);
			}
		} else {
			if(!game.isGameOver()) {
			player2.makeMove(game);
			notifyObservers();
			verificarStatus();
			}
		}
	}

	private void verificarStatus() {
		GameStatus status = game.getStatus();
		switch (status.getStatusAtual()) {
		case VITORIA:
			new PopUp("Vitória", "Parabéns, " + status.getVencedor().getNome() + " venceu!").mostrar();
			break;
		case EMPATE:
			new PopUp("Empate", "O jogo terminou em empate!").mostrar();
			break;
		default:
			break;
		}
	}
	@Override
	public void resetarGame() {
		game.resetGame();
		notifyObservers();
	}
}

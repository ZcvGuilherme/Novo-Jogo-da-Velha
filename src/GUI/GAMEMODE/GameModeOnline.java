package GUI.GAMEMODE;

import GAME.PLAYERS.HumanPlayer;
import GAME.STATUS.GameStatus;
import GUI.COMPONENTES.PopUp;
import GUI.OBSERVERS.Observer;
import GUI.TELAS.TelaGame;

public class GameModeOnline extends GameMode implements Observer{
	private boolean jogavel;
	private static HumanPlayer player1 = new HumanPlayer("Player1", 'X');
	private static HumanPlayer player2 = new HumanPlayer("Player2", 'O');
	public GameModeOnline() {
		super(player1, player2);
		this.jogavel = true;
	}

	@Override
	public void fazerJogada(int i, int j) {
		if (jogavel && game.play(i, j)) {
			notifyObservers();
			verificarStatus();
		}
	}
	
	private void verificarStatus() {
		GameStatus status = game.getStatus();
		switch (status.getStatusAtual()) {
		case VITORIA:
			new PopUp("Vitória", "Parabéns, " + status.getVencedor().getNome() + " venceu!").mostrar();
			jogavel = false;
			break;
		case EMPATE:
			new PopUp("Empate", "O jogo terminou em empate!").mostrar();
			jogavel = false;
			break;
		default:
			break;
		}
	}
	@Override
	public void resetarGame() {
		game.resetGame();
		notifyObservers();
		jogavel = true;
	}

	@Override
	public void update(GameStatus status) {
		game.setGameStatus(status);
		updateTelaOnly(status);
	}
	public void updateTelaOnly(GameStatus status) {
		for (Observer observer : observers) {
			if (observer instanceof TelaGame) {
				observer.update(status);
			}
		}
	}
	

}

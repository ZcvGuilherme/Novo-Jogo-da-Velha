package GUI.GAMEMODE;

import GAME.PLAYERS.HumanPlayer;
import GAME.STATUS.GameStatus;
import GUI.COMPONENTES.PopUp;


public class GameMode1v1 extends GameMode{
	private boolean jogavel;
	
	private static HumanPlayer hplayer1 = new HumanPlayer("Player1", 'X');
	private static HumanPlayer hplayer2 = new HumanPlayer("Player2", 'O');
	public GameMode1v1(String nome) {
		super(hplayer1.setNome(nome), hplayer2);
		this.jogavel = true;
		notifyObservers();
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
	//Fazer jogada deve: verificar jogada, e se, jogada for válida
	public void fazerJogada(int i, int j) {
		if (jogavel && game.play(i, j)) {
			notifyObservers();
			verificarStatus();
		}
	}
	public void resetarGame() {
		game.resetGame();
		notifyObservers();
		jogavel = true;
	}
}

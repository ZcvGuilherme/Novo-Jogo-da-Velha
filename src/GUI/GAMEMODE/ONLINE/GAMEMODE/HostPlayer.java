package GUI.GAMEMODE.ONLINE.GAMEMODE;

import GAME.PLAYERS.HumanPlayer;
import GAME.STATUS.GameStatus;
import GUI.COMPONENTES.PopUp;
import GUI.GAMEMODE.GameMode;
import GUI.OBSERVERS.Observer;
import GUI.TELAS.TelaGame;

public class HostPlayer extends GameMode implements Observer{
	private static final char HOST_SYMBOL = 'X';
	private boolean jogavel;
	private String nome;
	private static String nomeClient = "Waiting Client...";
	public HostPlayer(String nome) {
		super(new HumanPlayer(nome, HOST_SYMBOL), new HumanPlayer(nomeClient, 'O'));
		this.jogavel = true;
		this.nome = nome;
	}
	
	
	@Override
	public void fazerJogada(int i, int j) {
		if (jogavel && game.play(i, j)) {
			System.out.println("Eu sou o jogador "+ nome + "e estou fazendo a jogada");
			notifyObservers();
			verificarStatus();
			jogavel = isTurn();
		}
	}
	@Override
    public void update(GameStatus status) {
		System.out.println("Eu sou o jogador " + nome + " e estou atualizando");
        game.refresh(status);
        refreshClient(status);
        jogavel = isTurn();
        updateTelaOnly(status);
    }
	
	@Override
    public void resetarGame() {
        game.resetGame();
        jogavel = isTurn();
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
	private boolean isTurn() {
		return game.getCurrentPlayer().getsymbol() == HOST_SYMBOL;
	}
	private void updateTelaOnly(GameStatus status) {
		for (Observer observer : observers) {
			if (observer instanceof TelaGame) {
				observer.update(status);
			}
			
			
		}
	}
	private void refreshClient(GameStatus status) {
		nomeClient = status.getPlayer1();
        players.get(0).setNome(nomeClient);
	}
}

package GUI.GAMEMODE.ONLINE.GAMEMODE;

import GAME.PLAYERS.HumanPlayer;
import GAME.PLAYERS.TypePlayer;
import GAME.STATUS.GameStatus;
import GUI.COMPONENTES.PopUp;
import GUI.GAMEMODE.GameMode;
import GUI.OBSERVERS.Observer;
import GUI.TELAS.TelaGame;

public class GameModeOnline extends GameMode implements Observer{
	private boolean jogavel;
	private static HumanPlayer player1 = new HumanPlayer("Player1", 'X');
	private static HumanPlayer player2 = new HumanPlayer("Player2", 'O');
	private HumanPlayer ogPlayer;
	int numVar;
	public GameModeOnline(String nome, int numJogador) {
		super(player1, player2);
		this.numVar = numJogador;
		if (numJogador != 1 && numJogador != 2) {
	        throw new IllegalArgumentException("O valor de 'jogador' deve ser 1 ou 2.");
	    }
	    iniciarJogador(nome, numJogador);
	    notifyObservers();
		this.jogavel = true;
	}

	@Override
	public void fazerJogada(int i, int j) {
		System.out.println("Estou tentando fazer uma jogada");
		if (jogavel && game.play(i, j)) {
			System.out.printf("eu sou o %s e eu fiz a jogada em %d %d", ogPlayer.getNome(), i, j);
			notifyObservers();
			verificarStatus();
			jogavel = isTurn();
			System.out.println("Posso jogar agora?: " + jogavel);
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
		System.out.println("Eu sou o jogador: " + ogPlayer.getNome() + " E estou tentando atualizar");
		atualizarJogadores(status);
		game.refresh(status);
		jogavel = isTurn();
		updateTelaOnly(status);
		verificarStatus();
	}
	
	public void updateTelaOnly(GameStatus status) {
		for (Observer observer : observers) {
			if (observer instanceof TelaGame) {
				observer.update(status);
			}
		}
	}
	private boolean isTurn() {
		return ogPlayer.getsymbol() == game.getCurrentPlayer().getsymbol();
	}
	private void atualizarJogadores(GameStatus status) {
		TypePlayer playerAtual = status.getJogadorAtual();
		switch (playerAtual.getsymbol()) {
			case 'X':
				if (numVar == 1) {
					status.setJogadorAtual(ogPlayer);
					break;
				} else {
					player1.setNome(playerAtual.getNome());
					break;
				}
			case 'O':
				if (numVar == 2) {
					status.setJogadorAtual(ogPlayer);
					break;
				} else {
					player2.setNome(playerAtual.getNome());
				}
				
		}
	}
	private void iniciarJogador(String nome, int num) {
	    if (num == 1) {
	    	System.out.println("Eu sou o jogador 1, meu nome é: " + nome);
	        player1.setNome(nome);
	        ogPlayer = player1;
	        jogavel = true;
	      
	    } else {
	    	System.out.println("Eu sou o jogador 2, meu nome é: " + nome);
	        player2.setNome(nome);
	        ogPlayer = player2;
	        jogavel = false;
	    }
	    // Envia os nomes atualizados para sincronizar com o outro cliente
	    if (numVar == 2) {
	        notifyObservers();
	    }
	}
	
}

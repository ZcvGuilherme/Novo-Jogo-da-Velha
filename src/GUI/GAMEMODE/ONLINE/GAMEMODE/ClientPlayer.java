package GUI.GAMEMODE.ONLINE.GAMEMODE;

import GAME.PLAYERS.HumanPlayer;
import GAME.STATUS.GameStatus;
import GUI.COMPONENTES.PopUp;
import GUI.GAMEMODE.GameMode;
import GUI.OBSERVERS.Observer;
import GUI.TELAS.TelaGame;

public class ClientPlayer extends GameMode implements Observer {
    private static final char CLIENT_SYMBOL = 'O';
    private boolean jogavel;
    private String nome;
    private static String nomeHost = "Waiting Host...";

    public ClientPlayer(String nome) {
        super(new HumanPlayer(nomeHost, 'X'), new HumanPlayer(nome, CLIENT_SYMBOL));
        this.jogavel = false; // O cliente não começa jogando
        this.nome = nome;
    }

    @Override
    public void fazerJogada(int i, int j) {
        if (jogavel && game.play(i, j)) {
            System.out.println("Eu sou o jogador " + nome + " e estou fazendo a jogada");
            notifyObservers();
            verificarStatus();
            jogavel = isTurn();
        }
    }
    @Override
    public void update(GameStatus status) {
        System.out.println("Eu sou o jogador " + nome + " e estou atualizando");
        game.nextPlayer();
        status.setJogadorAtual(game.getCurrentPlayer());
        game.refresh(status);
        jogavel = isTurn();
        updateTelaOnly(game.getStatus());
        System.out.println("Jogador 2 atualizando");
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
        return game.getCurrentPlayer().getsymbol() == CLIENT_SYMBOL;
    }

    private void updateTelaOnly(GameStatus status) {
        for (Observer observer : observers) {
            if (observer instanceof TelaGame) {
                observer.update(status);
            }
        }
    }

}

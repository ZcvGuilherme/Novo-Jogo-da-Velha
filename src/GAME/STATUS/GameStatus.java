package GAME.STATUS;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import GAME.PLAYERS.TypePlayer;
import GAME.TABULEIRO.Board;

public class GameStatus implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Status statusAtual;
	private List<ButtonState> botoes;
	private TypePlayer jogadorAtual;
	private TypePlayer vencedor;
	private String player;

	public GameStatus(int size) {
		this.statusAtual = Status.EM_ANDAMENTO;
		this.botoes = new ArrayList<>();
		criarBotoes(size);
		}
	
	public void atualizarStatus(Status novoStatus, TypePlayer vencedor) {
		this.statusAtual = novoStatus;
		this.vencedor = vencedor;
	}
	public void criarBotoes(int size) {
		for (int i = 0; i < size; i++) {
	        for (int j = 0; j < size; j++) {
	        	ButtonState botao = new ButtonState(false, ' ', i, j);
	        	botoes.add(botao);
	        }
	    }
	}
	public void atualizarBotoes(Board board) {
		for (int i = 0; i < board.getSize(); i++) {
	        for (int j = 0; j < board.getSize(); j++) {
	        	char simbolo = board.getSlot(i, j);
	        	
	        	for (ButtonState botao : botoes) {
	        		if (botao.getI() == i && botao.getJ() == j) {
	        			botao.setSimbolo(simbolo);
	        			botao.setClicado(simbolo != ' ');
	        			break;
	        		}
	        	}
	        }
	    }
	}
	
    public void setJogadorAtual(TypePlayer jogador) {
        this.jogadorAtual = jogador;
    }

    public Status getStatusAtual() {
        return statusAtual;
    }

    public TypePlayer getJogadorAtual() {
        return jogadorAtual;
    }

    public TypePlayer getVencedor() {
        return vencedor;
    }

	public List<ButtonState> getBotoes() {
		return botoes;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

}

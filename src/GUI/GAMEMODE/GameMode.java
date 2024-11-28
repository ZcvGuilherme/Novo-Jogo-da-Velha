package GUI.GAMEMODE;

import java.util.ArrayList;
import java.util.List;
import GAME.Game;
import GAME.PLAYERS.TypePlayer;
import GUI.OBSERVERS.Observable;
import GUI.OBSERVERS.Observer;

public abstract class GameMode implements Observable{
	protected List<Observer> observers;
	protected Game game;
	private List<TypePlayer> players;
	public GameMode(TypePlayer player1, TypePlayer player2) {
		this.players = new ArrayList<>();
		players.add(player1);
		players.add(player2);
		this.game = new Game(players, 3);
		this.observers = new ArrayList<>();
		}
	public TypePlayer getCurrentPlayer() {
		return game.getCurrentPlayer();
	}
	public char getSymbolChar(int i, int j) {
		return game.getPosition(i, j);
	}
	public abstract void fazerJogada(int i, int j);
	
	public abstract void resetarGame();

	public void addObserver(Observer observer) {
		this.observers.add(observer);
		notifyObservers();
	}
	
	public void removeObserver(Observer observer) {
		this.observers.remove(observer);
	}

	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(game.getStatus());
		}
	
	}
}

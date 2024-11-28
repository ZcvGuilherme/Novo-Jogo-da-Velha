package GAME;

import java.util.ArrayList;
import java.util.List;

import GAME.PLAYERS.HumanPlayer;
import GAME.PLAYERS.TypePlayer;

public class Testes {

	public static void main(String[] args) {
		HumanPlayer player1 = new HumanPlayer("nome", 'X');
		HumanPlayer player2 = new HumanPlayer("nome", 'O');
		List<TypePlayer> lista = new ArrayList<>();
		lista.add(player1);
		lista.add(player2);
		Game game = new Game(lista, 3);
		System.out.println("Teste");
		game.play(0, 0);
		System.out.println(game.getPosition(0, 0));
		game.play(1, 0);
		if (game.getPosition(1, 1) == ' ') {
			System.out.println("nulo");
		}
		System.out.println(game.getPosition(1, 1));
		System.out.println(game.getPosition(1, 0));
	}

}

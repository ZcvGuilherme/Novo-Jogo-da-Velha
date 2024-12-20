package TERMINAL;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import GAME.Game;
import GAME.PLAYERS.BotPlayer;
import GAME.PLAYERS.HumanPlayer;
import GAME.PLAYERS.TypePlayer;

public class VsBotMain {
	public static void main(String[] Args){
        HumanPlayer player1 = new HumanPlayer("Player 1", 'X');
        BotPlayer player2 = new BotPlayer("Player 2", 'O');
        List<TypePlayer> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Scanner scanner = new Scanner(System.in);
        Game game = new Game(players, 3);
        boolean endGame = game.isGameOver();
        TypePlayer currentPlayer = game.getCurrentPlayer();

        while (!endGame) { 
            LimpaTela();
            if (endGame){
                System.out.println("Ainda nao acabou...");
            }
            game.mostrarTabuleiro();
            currentPlayer = game.getCurrentPlayer();
            System.out.println("Vez de " + currentPlayer.getNome() + "!!");
            if (currentPlayer == player1) {
            	System.out.println("Digite a linha: ");
                int i = scanner.nextInt();
                System.out.println("Digite a coluna: ");
                int j = scanner.nextInt();
                player1.setMove(i, j);
                if (player1.makeMove(game)){
                    System.out.println("Jogada feita!");
                } else {
                    System.out.println("Jogada inválida! Tente novamente");
                }
            } else {
            	player2.makeMove(game);
            }
            
            endGame = game.isGameOver();
        }
        LimpaTela();
        game.mostrarTabuleiro();
        System.out.println("Jogo encerrado!");
        System.out.println(currentPlayer.getNome() + " Venceu!!");
        scanner.close();
    }
    private static void LimpaTela(){
        Utilitario.LimpaTela();
    }
}

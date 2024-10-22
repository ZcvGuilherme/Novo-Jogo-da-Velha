package TERMINAL;

import GAME.Game;
import GAME.PLAYERS.Player;
import GAME.TABULEIRO.Board;
import GAME.VERIFICADOR.RuleChecker;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] Args){
        Player player1 = new Player(1, "Player 1", 'X');
        Player player2 = new Player(2, "Player 2", 'O');
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        RuleChecker ruleChecker = new RuleChecker();
        Board board = new Board(3);
        Scanner scanner = new Scanner(System.in);
        Game game = new Game(players, board, ruleChecker);
        boolean endGame = game.GameOver();
        Player currentPlayer = game.getCurrentPlayer();
        while (!endGame) { 
            LimpaTela();
            if (endGame){
                System.out.println("Ainda nao acabou...");
            }
            game.mostrarTabuleiro();
            currentPlayer = game.getCurrentPlayer();
            System.out.println("Vez de " + currentPlayer.getNome() + "!!");
            System.out.println("Digite a linha: ");
            int i = scanner.nextInt();
            System.out.println("Digite a coluna: ");
            int j = scanner.nextInt();

            if (game.play(i, j)){
                System.out.println("Jogada feita!");
            } else {
                System.out.println("Jogada inválida! Tente novamente");
            }
            endGame = game.GameOver();
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

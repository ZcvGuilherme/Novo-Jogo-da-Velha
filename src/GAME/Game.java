package GAME;

import GAME.PLAYERS.Player;
import GAME.TABULEIRO.Board;
import GAME.VERIFICADOR.RuleChecker;
import java.util.List;

public class Game {
    private List<Player> players;
    private Board board;
    private RuleChecker ruleChecker;
    private Player currentPlayer;
    private int indexPlayer;
    private char lastPlayer;
    
    public Game(List<Player> players, int size) {
        if (players == null || players.size() < 2){
        throw new IllegalArgumentException("O jogo requer no mínimo 2 jogadores");}
        this.players = players;
        this.board = new Board(size);
        this.indexPlayer = 0;
        this.ruleChecker = new RuleChecker(board);
        this.currentPlayer = players.get(indexPlayer);
    }
    public void nextPlayer(){
        indexPlayer = (indexPlayer + 1) % players.size();
        currentPlayer = players.get(indexPlayer);
    }
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    /** 
     * Método para atualizar o tabuleiro, se a jogada for válida
     * @param i A linha onde o jogador deseja jogar
     * @param j A coluna onde o jogador deseja jogar
     * @return retorna true caso a jogada seja válida, false caso seja inválida
     */
    public boolean play(int i, int j){
        if (ruleChecker.isValidMove(i, j)){
        lastPlayer = currentPlayer.getsymbol();
        board.setPlay(i, j, lastPlayer);
        nextPlayer();
        isGameOver();
        return true;
    } else {
        //Faça aqui alguma mudança de lógica caso necessário retornar algo
        System.out.println("Erro! Jogada inválida");
        return false;
        }
    }
    public boolean isGameOver(){
        return ruleChecker.isGameOver(lastPlayer);
    }
    public boolean isDraw(){
        return ruleChecker.isDraw();
    }
    public boolean isWin(){
        return ruleChecker.checkWin(lastPlayer);
    }
/**
 * Exibe o tabuleiro no terminal, independentemente do tamanho.
 * Este método imprime o tabuleiro atual, adicionando separadores para melhorar a visualização.
 */
    public void resetGame() {
        board.clear();
    }
    public void mostrarTabuleiro() {
    int size = board.getSize();
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            System.out.print(" " + board.getSlot(i, j));
            if (j < size - 1) {
                System.out.print(" |");
            }
        }
        System.out.println(); // Quebra de linha após cada linha do tabuleiro

        // Adicionar um separador de linha, exceto após a última linha
        if (i < size - 1) {
            for (int j = 0; j < size; j++) {
                System.out.print("---");
                if (j < size - 1) {
                    // Adicionar o sinal de cruz entre os separadores
                    System.out.print("+");
                }
            }
            System.out.println(); // Quebra de linha para o separador
        }
    }
}
}

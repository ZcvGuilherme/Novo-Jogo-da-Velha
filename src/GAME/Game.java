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
    /**Construtor da Classe Game, une os principais componentes do jogo da velha, como players, tabuleiro e regras. Essa classe gerencia a lógica do jogo, alternância dos jogadores e atualização do tabuleiro
     * 
     * @param players               Lista de jogadores que participarão do jogo
     * @param board                 O tabuleiro onde será jogado o jogo
     * @param ruleChecker           O verificador das regras e validador de jogadas
     * @throws IllegalArgumentException Se  a lista de jogadores for nula ou tiver menos de 2 jogadores
     */
    public Game(List<Player> players, Board board, RuleChecker ruleChecker) {
        if (players == null || players.size() < 2){
        throw new IllegalArgumentException("O jogo requer no mínimo 2 jogadores");}
        this.players = players;
        this.board = board;
        this.indexPlayer = 0;
        this.currentPlayer = players.get(indexPlayer);
        this.ruleChecker = ruleChecker;
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
        if (ruleChecker.isValidMove(i, j, board)){
        lastPlayer = currentPlayer.getsymbol();
        board.setPlay(i, j, lastPlayer);
        nextPlayer();
        return true;
    } else {
        //Faça aqui alguma mudança de lógica caso necessário retornar algo
        System.out.println("Erro! Jogada inválida");
        return false;
        }
    }
    public boolean GameOver(){
        return ruleChecker.isGameOver(lastPlayer, board);
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
        // Imprimir os elementos da linha
        for (int j = 0; j < size; j++) {
            System.out.print(" " + board.getSlot(i, j));
            if (j < size - 1) {
                // Adicionar um separador de coluna, exceto após o último elemento
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

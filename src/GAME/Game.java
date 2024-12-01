package GAME;

import java.util.List;
import java.util.Random;
import GAME.PLAYERS.TypePlayer;
import GAME.STATUS.GameStatus;
import GAME.STATUS.Status;
import GAME.TABULEIRO.Board;
import GAME.VERIFICADOR.RuleChecker;

public class Game {
	private GameStatus status;
    private List<TypePlayer> players;
    private Board board;
    private RuleChecker ruleChecker;
    private TypePlayer currentPlayer;
    private int indexPlayer;
    private TypePlayer lastPlayer;
    
    public Game(List<TypePlayer> players, int size) {
        if (players == null || players.size() < 2){
        throw new IllegalArgumentException("O jogo requer no mínimo 2 jogadores");}
        this.players = players;
        this.board = new Board(size);
        this.indexPlayer = 0;
        this.ruleChecker = new RuleChecker(board);
        this.currentPlayer = players.get(indexPlayer);
        this.lastPlayer = currentPlayer;
        this.status = new GameStatus(size);
        StateUpdate();
    }
    //------------------------STATUS GAME COMMANDS-----------------------\\
    public void StateUpdate() {
        status.atualizarBotoes(board);
        status.setJogadorAtual(currentPlayer);
        if (isWin()) { // Verifica vitória primeiro
            status.atualizarStatus(Status.VITORIA, lastPlayer);
        } else if (isDraw()) { // Verifica empate depois
            status.atualizarStatus(Status.EMPATE, null);
        } else {
            status.atualizarStatus(Status.EM_ANDAMENTO, null);
        }
    }
    
    //-------------------------------------------------------------------\\
    public void nextPlayer(){
        indexPlayer = (indexPlayer + 1) % players.size();
        currentPlayer = players.get(indexPlayer);
    }
    public TypePlayer getCurrentPlayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(int index) {
    	this.currentPlayer = players.get(index);
    }
    /** 
     * Método para atualizar o tabuleiro, se a jogada for válida
     * @param i A linha onde o jogador deseja jogar
     * @param j A coluna onde o jogador deseja jogar
     * @return retorna true caso a jogada seja válida, false caso seja inválida
     */
    public boolean play(int i, int j){
        if (ruleChecker.isValidMove(i, j)){
        lastPlayer = currentPlayer;
        board.setPlay(i, j, lastPlayer.getsymbol());
        nextPlayer();
        isGameOver();
        StateUpdate();
        return true;
    } else {
        //Faça aqui alguma mudança de lógica caso necessário retornar algo
        System.out.println("Erro! Jogada inválida");
        return false;
        }
    }
    public int getBoardSize() {
    	return board.getSize();
    }
    public boolean isGameOver(){
        return ruleChecker.isGameOver(lastPlayer.getsymbol());
    }
    public boolean isDraw(){
        return ruleChecker.isDraw();
    }
    public boolean isWin(){
        return ruleChecker.checkWin(lastPlayer.getsymbol());
    }
    /**
     * Exibe o tabuleiro no terminal, independentemente do tamanho.
     * Este método imprime o tabuleiro atual, adicionando separadores para melhorar a visualização.
     */
    public void resetGame() {
        board.clear();
        resetPlayer();
        StateUpdate();
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
    private void resetPlayer(){
        indexPlayer = 0;
        currentPlayer = players.get(indexPlayer);
    }
    public void randomPlayer() {
    	Random random = new Random();
    	indexPlayer = random.nextInt(players.size());
    	currentPlayer = players.get(indexPlayer);
    }
    public char getPosition(int i, int j) {
    	return board.getSlot(i, j);
    }
    public GameStatus getStatus() {
    	return status;
    }
    public void setGameStatus(GameStatus status) {
    	this.status = status;
    }
}

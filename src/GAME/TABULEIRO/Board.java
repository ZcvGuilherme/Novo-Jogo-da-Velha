package GAME.TABULEIRO;

public class Board {
    private final int size;
    private final char[][] board;
    
    public Board(int size){
        this.size = size;
        board = new char[size][size];
        for (int i = 0; i <size; i++){
            for (int j = 0; j < size; j++){
                board[i][j] = ' ';
            }
        }
    }
    /*
     *    |   |
     * ---+---+----
     *    |   |
     * ---|---|----
     *    |   |
     */
    public char[][] getBoard(){
        return board;
    }
    public int getSize(){
        return size;
    }
    public char getSlot(int i, int j){
        return board[i][j];
    }
    public void clear(){
        for (int i = 0; i <size; i++){
            for (int j = 0; j < size; j++){
                board[i][j] = ' ';
            }
        }
    }
    public void setPlay(int i, int j, char element){
        board[i][j] = element;
    }
    public char[] getLine(int line){
        if (line < 0 || line >= size){
            throw new IllegalArgumentException("Índice fora dos limites do tabuleiro");
        }
        return board[line];
    }
    public char [] getCol(int col){
        if (col < 0 || col >= size) {
            throw new IllegalArgumentException("Índice fora dos limites do tabuleiro");
        }
        char[] column = new char[size];
        for (int i = 0; i < size; i++){
            column[i] = board[i][col];
        }
        return column;
    }
    /**
     * @param diag É o numero da diagonal que será recebida
     * Este método funciona apenas com 1 ou 2, qualquer outro valor será considerado inválido.
     * Ao colocar 1, ele vai retornar a primeira diagonal, da esquerda para a direita
     * Ao colocar 2, ele vai retornar a segunda diagonal, da direita para a esquerda
     * Note que ele retorna apenas 1 diagonal por vez.
     */
    public char[] getDiag(int diag) {
        char[] diagonal = new char[size];
        if (diag == 1) {
            // Primeira diagonal (da esquerda para a direita)
            for (int i = 0; i < size; i++) {
                diagonal[i] = board[i][i];
            }
        } else if (diag == 2) {
            // Segunda diagonal (da direita para a esquerda)
            for (int i = 0; i < size; i++) {
                diagonal[i] = board[i][size - i - 1];
            }
        } else {
            throw new IllegalArgumentException("O número da diagonal deve ser 1 ou 2");
        }
        return diagonal;
    }

}

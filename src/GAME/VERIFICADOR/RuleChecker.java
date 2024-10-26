package GAME.VERIFICADOR;

import GAME.TABULEIRO.Board;


public class RuleChecker {
    private Board board;
    public RuleChecker(Board board){
        this.board = board;
    }
    /** 
     * Método principal para verificar se o jogo acabou.
     * @param simbol - Recebe um char do jogador para verificar as condições
     * @param board - Recebe o tabuleiro para fazer as verificações
     * @return - retorna true caso o jogo tenha sido encerrado, ou seja, caso alguma condição de vitória ou a condição de empate tenha sido atingida
     */
    public boolean isGameOver(char simbol){
        return checkWin(simbol) || isDraw();
    }

    /**
     * Verifica se um slot especifico está vazio
     * @param i - Equivalente a linha
     * @param j - Equivalente a coluna
     * @param board - precisa receber board para usar o método getSlot
     * @return - retorna true caso esteja vazio, permitindo a inserção de um novo elemento 
     */
    public boolean isValidMove(int i, int j){
        int boardSize = board.getSize();
        if (i < 0 || i >= boardSize || j < 0 || j >= boardSize){
            return false;
        } else if(!isEmpty(i, j)) {
            return false;
        }
        return true;
    }
    private boolean isEmpty(int i, int j) {
        return board.getSlot(i, j) == ' ';
    }
    /**
     * Responsável por fazer todas as verificações de uma vez
     * Verifica Linha, Coluna e Diagonal usando o método checkChar
     * @param simbol - precisa ser o simbolo do player
     * @param board - precisa receber o campo para fazer a verificação
     * @return - retorna true caso alguma condição do checkChar tenha sido atingida, retorna false caso não haja condição de vitória
     */
    public boolean checkWin(char simbol) {
        for (int i = 0; i < board.getSize(); i++) {
            if (checkChar(simbol, board.getLine(i)) || checkChar(simbol, board.getCol(i))) {
                System.out.println("Vitória");
                return true;
            }
        }
        // Verificar as diagonais fora do laço
        if (checkChar(simbol, board.getDiag(1)) || checkChar(simbol, board.getDiag(2))) {
            System.out.println("Vitória");
            return true;
        }
        return false;
    }

    /**
     * Responsável por verificar empates, que no caso seria caso o campo inteiro se preencheu sem ativar a condição de vitória.
     * @param board - recebe o board para verificar slot por slot.
     * @return - retorna false caso ainda tenha algum espaço vazio, mas retorna true caso contrário.
     * 
     */
    public boolean isDraw(){
        
        int size = board.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++){
                if (isEmpty(i, j))
                return false;
            }
        }
        System.out.println("Empate");
        return true;
    }   
    

    /** Método para verificar se todos os elementos de um char[] são os mesmos, método único feito para receber e verificar qualquer linha, coluna ou diagonal, independente do tamanho.
     * @param simbol - simbolo do jogador a ser avaliado
     * @param lista - Recebe um char[], método feito pensado em receber os getters de coluna, linha e daigonal do objeto Board.
     * @return - retorna falso caso haja um elemento na lista que seja diferente do simbolo, retorna true caso todos os elementos da lista sejam iguais
     */
    private boolean checkChar(char simbol, char[] lista){
        for (char element : lista){
            if (element != simbol){
                return false;
            }
        }
        return true;
    }

}

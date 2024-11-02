package GAME.PLAYERS;

public abstract class Player implements TypePlayer{

    protected String nome;
    protected char symbol;
    /**
     * Construtor do Player. Inicialmente somente com o ID, Nome e symbol como parametros
     * @param nome - Nome a ser usado in-game 
     * @param symbol - Normalmente X ou O para jogo da velha
     */
    public Player(String nome, char symbol) {
        
        this.nome = nome;
        this.symbol = symbol;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getsymbol() {
        return symbol;
    }

    public void setsymbol(char symbol) {
        this.symbol = symbol;
    }
    
}

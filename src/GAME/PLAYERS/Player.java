package GAME.PLAYERS;

public class Player {
    private int id;
    private String nome;
    private char symbol;
    /**
     * Construtor do Player. Inicialmente somente com o ID, Nome e symbol como parametros
     * @param id - ID para melhor identificação do player
     * @param nome - Nome a ser usado in-game 
     * @param symbol - Normalmente X ou O para jogo da velha
     */
    public Player(int id, String nome, char symbol) {
        this.id = id;
        this.nome = nome;
        this.symbol = symbol;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

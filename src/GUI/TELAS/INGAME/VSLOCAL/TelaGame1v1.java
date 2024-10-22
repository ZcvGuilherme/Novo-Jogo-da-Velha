package GUI.TELAS.INGAME.VSLOCAL;

import GAME.Game;
import GAME.PLAYERS.Player;
import GAME.TABULEIRO.Board;
import GAME.VERIFICADOR.RuleChecker;
import GUI.TELAS.INGAME.TelaGame;
import java.util.ArrayList;
import java.util.List;

public class TelaGame1v1 extends TelaGame{
    private Game game;
    private final Player player1;
    private Player player2;
    private Board board;
    private RuleChecker ruleChecker;
    private List<Player> players;
    
    public TelaGame1v1(String NomeJogador1){
        super(3, NomeJogador1, "Jogador 2", game);
        this.player1 = new Player(1, NomeJogador1, 'X');
        this.player2 = new Player(2, "Jogador 2", 'O');
        this.board = new Board(3);
        this.ruleChecker = new RuleChecker();
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        this.game = new Game(players, board, ruleChecker);
        
    }
}

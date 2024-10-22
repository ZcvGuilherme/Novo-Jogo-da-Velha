package GUI.COMPONENTES;

import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CriarComponente {
    public static Botao criarBotao(String texto, ActionListener action) {
        Botao button = new Botao(texto); // Usando seu botão personalizado
        button.addActionListener(action);
        return button;
    }
    public static BotaoGame criarBotaoGame(ActionListener action){
        BotaoGame button = new BotaoGame();
        button.addActionListener(action);
        return button;
    }
    public static BotaoGame[][] criarBotoes(int size, JPanel painel, ActionListener action) {
        BotaoGame[][] botoesGame = new BotaoGame[size][size];
        int buttonSize = 100;
        int posX = 100;
        int posY = 100;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                botoesGame[i][j] = criarBotaoGame(action);
                botoesGame[i][j].setBounds(posX + j * buttonSize, posY + i * buttonSize, buttonSize, buttonSize);
                botoesGame[i][j].setI(i);
                botoesGame[i][j].setJ(j);
                painel.add(botoesGame[i][j]);
            }
        }
        return botoesGame;
    }
    public static JTextField criarCampoTexto() {
        return new JTextField();
    }
    public static JLabel criarLabel(String Texto){
        return new JLabel(Texto);
    }

    public static void adicionarBarras(JComponent componente, int size, int tamanhoBotao, int offset, int grossura){
        new Barras(componente, size, tamanhoBotao, offset, grossura);
    }

    
}

package GUI.COMPONENTES;

import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GUI.COMPONENTES.BOTOES.Botao;
import GUI.COMPONENTES.BOTOES.BotaoGame;

public class CriarComponente {
    public static Botao criarBotao(String texto, ActionListener action) {
        Botao button = new Botao(texto); // Usando seu botão personalizado
        button.addActionListener(action);
        return button;
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

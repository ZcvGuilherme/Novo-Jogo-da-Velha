package GUI.TELAS.OUTGAME;

import java.awt.Dimension;
import javax.swing.JFrame;

public abstract class TelaGenerica {
    protected JFrame tela;

    public TelaGenerica(String titulo, int width, int height, int x, int y, boolean resize){
        tela = new JFrame(titulo);
        tela.setSize(width, height);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLayout(null);
        tela.setLocation(x, y);
        tela.setResizable(resize);
        tela.setMinimumSize(new Dimension(width, height));
    }
    public void mostrar(){
        tela.setVisible(true);
    }
    public void fechar(){
        tela.dispose();
    }
    public void setVisible(boolean viz){
        tela.setVisible(viz);
    }
}

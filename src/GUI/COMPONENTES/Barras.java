package GUI.COMPONENTES;

import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class Barras {
    public Barras(JComponent componente, int size, int tamanhoBotao, int offset, int grossura){
        int tamanhoTotal = tamanhoBotao * size;

        for (int i = 1; i < size; i++) {
            JPanel verticalLine = new JPanel();
            verticalLine.setBackground(Color.BLACK);
            verticalLine.setBounds(offset + i * tamanhoBotao - (grossura / 2), offset, grossura, tamanhoTotal);
            componente.add(verticalLine);
            verticalLine.setVisible(true);
        }

        // Adiciona barras horizontais
        for (int i = 1; i < size; i++) {
            JPanel horizontalLine = new JPanel();
            horizontalLine.setBackground(Color.BLACK);
            horizontalLine.setBounds(offset, offset + i * tamanhoBotao - (grossura / 2), tamanhoTotal, grossura);
            componente.add(horizontalLine);
            horizontalLine.setVisible(true);
        }
        
    }
}

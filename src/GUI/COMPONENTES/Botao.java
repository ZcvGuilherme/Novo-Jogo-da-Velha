package GUI.COMPONENTES;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Botao extends JButton{
    private Color corOriginal;
    private Color hover;
    
    public Botao(String texto){
        super(texto);
        this.corOriginal = Color.GRAY;
        hover = corOriginal.brighter();
        configure();
        mouseListener();
    }
    private void mouseListener(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hover);
            }
    
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(corOriginal);
            }
        });
    }
    private void configure(){
        setFont(new Font("Arial", Font.BOLD, 20));
        setBackground(corOriginal);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setOpaque(true);
        setFocusPainted(false);
        
    }
    public Color getCorOriginal() {
        return corOriginal;
    }
    public void setCorOriginal(Color corOriginal) {
        this.corOriginal = corOriginal;
        setBackground(corOriginal);
    }
    public Color getHover() {
        return hover;
    }
    public void setHover(Color hover) {
        this.hover = hover;
    }
    
    


}

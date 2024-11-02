package GUI.COMPONENTES.BOTOES;
import java.awt.Color;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
public class BotaoGame extends JButton{
    private boolean clicavel;
    private int i;
    private int j;
    private boolean Clicado;
    public BotaoGame(){
        super("");
        this.clicavel = true;   
        this.Clicado = false;
        configure();
    }
    private void configure(){
        setContentAreaFilled(false); 
        setBorderPainted(false); 
        setFocusPainted(false); 
        setOpaque(false); 
        setBorder(new EmptyBorder(5, 5, 5, 5)); 
        setBackground(Color.WHITE);
    }
    public boolean foiClicado() {
        return Clicado;
    }
    public void setFoiClicado(boolean clicado) {
        this.Clicado = clicado;
    }
    public void setClicavel(boolean click){
        this.clicavel = click;
    }
    
    public boolean getClicavel(){
        return clicavel;
    }

    public void limparBotao(){
        setIcon(null);
        setClicavel(true);
    }
    public Icon getImage(){
        return getIcon();
    }
    public void setImage(char caracter) {
        URL imageUrl = getClass().getResource("/GAME/IMAGES/" + (caracter == 'X' ? "X.png" : "BOLA.png"));
        if (imageUrl == null) {
            System.out.println("Imagem não encontrada para o caractere: " + caracter);
            setIcon(null); // Não exibe nenhuma imagem se a URL for null
        } else {
            setIcon(new ImageIcon(imageUrl));
        }
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }
    
}

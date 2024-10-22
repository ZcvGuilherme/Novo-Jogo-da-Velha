package GUI.COMPONENTES;
import java.awt.Color;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
public class BotaoGame extends JButton{
    private boolean clicavel;
    private int i;
    private int j;
    
    public BotaoGame(){
        super("");
        this.clicavel = true;   
        configure();
    }
    private void configure(){
        setContentAreaFilled(false); // Permite que o botão seja preenchido
        setBorderPainted(false); // Permite que a borda do botão seja desenhada
        setFocusPainted(false); // Remove o foco visual do botão
        setOpaque(false); // Torna o botão visível
        setBorder(new EmptyBorder(5, 5, 5, 5)); // Define uma borda visível
        setBackground(Color.WHITE);
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

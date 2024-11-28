package GUI.TELAS.OUTGAME;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import GUI.COMPONENTES.CriarComponente;
import GUI.TELAS.Iniciar;
import GUI.TELAS.TelaGenerica;


public class AddPlayer extends TelaGenerica{
    private JLabel label;
    private JTextField campoTexto;
    private JButton botao;

    public AddPlayer(){
        super("Bem Vindo!", 300, 200, 500, 200, false);

        label = CriarComponente.criarLabel("Digite seu nome");
        campoTexto = CriarComponente.criarCampoTexto();
        botao = CriarComponente.criarBotao("Confirmar", e -> comandoBotao());

        label.setBounds(100, 20, 200, 30);
        campoTexto.setBounds(50, 60, 200, 30);
        botao.setBounds(100, 110, 100, 30);
        
        tela.add(label);
        tela.add(campoTexto);
        tela.add(botao);
    }
    
    private void comandoBotao(){
        String texto = campoTexto.getText().trim();
        if (texto.isEmpty()){
            Border bordaOG = campoTexto.getBorder();
            campoTexto.setBorder(new LineBorder(Color.red, 2));
            Timer timer = new Timer(2000, e -> {
                campoTexto.setBorder(bordaOG);
            });
            timer.setRepeats(false);
            timer.start();
        } else {
            Iniciar.TelaPrincipal(texto);
            fechar();
        }
    }
   
}

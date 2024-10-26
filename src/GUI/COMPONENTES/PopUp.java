package GUI.COMPONENTES;

import javax.swing.JButton;
import javax.swing.JLabel;

import GUI.TELAS.TelaGenerica;

public class PopUp extends TelaGenerica{
    JLabel label;
    JButton botaoOK;
    public PopUp(String nome, String texto){
        super(nome, 300, 200, 500, 200, false);
        label = CriarComponente.criarLabel(texto);
        botaoOK = CriarComponente.criarBotao("Sair", e -> fechar());

        label.setBounds(90, 20, 400, 100);
        botaoOK.setBounds(110, 100, 60, 50);
        
        tela.add(label);
        tela.add(botaoOK);
    }

}

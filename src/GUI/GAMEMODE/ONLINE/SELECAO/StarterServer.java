package GUI.GAMEMODE.ONLINE.SELECAO;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import GUI.COMPONENTES.CriarComponente;
import GUI.TELAS.TelaGenerica;

public class StarterServer extends TelaGenerica{
	private JTextField campoPorta;
	private JButton botaoCancelar;
	private JLabel loadingGif;
	
	public StarterServer() {
		super("Iniciar Servidor", 100, 100, 50, 50, false);
		
		campoPorta = CriarComponente.criarCampoTexto();
		campoPorta.setBounds(150, 150, 70, 70);
		
		botaoCancelar = CriarComponente.criarBotao("Cancelar", null);
		
	}
	

}

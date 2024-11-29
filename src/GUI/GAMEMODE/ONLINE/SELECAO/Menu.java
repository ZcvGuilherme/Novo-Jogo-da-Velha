package GUI.GAMEMODE.ONLINE.SELECAO;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

import GUI.COMPONENTES.CriarComponente;
import GUI.TELAS.TelaGenerica;

public class Menu extends TelaGenerica {
    private JLabel campo;
    private JButton servidor;
    private JButton cliente;

    public Menu() {
        super("Menu Online", 400, 400, 200, 100, false);
        
        // Configurar fontes
        Font labelFont = new Font("Arial", Font.BOLD, 24);
        Font buttonFont = new Font("Arial", Font.BOLD, 24);

        // Dimensões da tela
        int telaLargura = tela.getWidth();
        int telaAltura = tela.getHeight();

        // Dimensões e posições dinâmicas dos componentes
        int labelLargura = 200;
        int labelAltura = 50;
        int labelX = (telaLargura - labelLargura) / 2; // Centralizado
        int labelY = 30; // Margem superior

        int botaoLargura = 130;
        int botaoAltura = 70;
        int espacoEntreBotoes = 40; // Espaço horizontal entre os botões

        // Calculando posições para os botões
        int botaoY = labelY + labelAltura + 50; // Abaixo do label
        int servidorX = (telaLargura - (botaoLargura * 2 + espacoEntreBotoes)) / 2;
        int clienteX = servidorX + botaoLargura + espacoEntreBotoes;

        // Criar e configurar componentes
        campo = CriarComponente.criarLabel("INICIAR COMO: ");
        campo.setFont(labelFont);
        campo.setBounds(labelX, labelY, labelLargura, labelAltura);

        servidor = CriarComponente.criarBotao("Servidor", e -> iniciarServer());
        servidor.setFont(buttonFont);
        servidor.setBounds(servidorX, botaoY, botaoLargura, botaoAltura);

        cliente = CriarComponente.criarBotao("Cliente", e -> iniciarCliente());
        cliente.setFont(buttonFont);
        cliente.setBounds(clienteX, botaoY, botaoLargura, botaoAltura);

        // Adicionar componentes à tela
        tela.add(campo);
        tela.add(servidor);
        tela.add(cliente);
    }
    
    private void iniciarServer() {
    	
    }
    private void iniciarCliente() {
    	
    }
}

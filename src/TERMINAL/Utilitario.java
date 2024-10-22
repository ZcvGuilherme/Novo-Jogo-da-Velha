package TERMINAL;

public class Utilitario {
    
    public static void LimpaTela() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            
            if (os.contains("win")) {
                // Comando para Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            // Tratamento de exceção caso ocorra algum erro
            System.out.println("Erro ao tentar limpar a tela: " + e.getMessage());
        }
    }
}

package CONNECTION.TESTES;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import GUI.GAMEMODE.GameModeOnline;

public class Server {

	private static final int PORT = 12345;
    private static final List<Socket> clients = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado na porta " + PORT);
            System.out.println("Aguardando dois clientes...");

            while (clients.size() < 2) {                   
                Socket clientSocket = serverSocket.accept();
                clients.add(clientSocket);
                System.out.println("Novo cliente conectado: " + clientSocket.getInetAddress());
                new Thread(new ClientHandler(clientSocket, clients)).start();
            }

            System.out.println("Dois clientes conectados. Pronto para troca de mensagens!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void startServer() {
    	try (ServerSocket serverSocket = new ServerSocket(PORT)){
    		System.out.println("Servidor iniciado na porta " + PORT);
    		System.out.println("Aguardando dois clientes....");
    		
    		while (clients.size() < 2) {
    			Socket clientSocket = serverSocket.accept();
    			clients.add(clientSocket);
    			System.out.println("Novo cliente conectado: " + clientSocket.getInetAddress());
    			new Thread(new ClientHandler(clientSocket, clients)).start();
    		}
    		
    		System.out.println("2 clientes conectaram");
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
}

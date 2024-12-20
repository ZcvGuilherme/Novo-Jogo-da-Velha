package CONNECTION.SERVER;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import CONNECTION.CLIENT.ClientHandler;

public class GameServer {
	
	private static final int PORT = 12345;
    private static final List<Socket> clients = Collections.synchronizedList(new ArrayList<>());
    
	public void startServer() {
		Broadcaster broadcaster = new Broadcaster(clients);
		
    	try (ServerSocket serverSocket = new ServerSocket(PORT)){
    		
    		System.out.println("Servidor iniciado na porta " + PORT);
    		System.out.println("Aguardando dois clientes....");
    		
    		while (clients.size() < 2) {
    		    Socket clientSocket = serverSocket.accept();
    		    broadcaster.addClient(clientSocket);
    		    System.out.println("Novo cliente conectado: " + clientSocket.getInetAddress());
    		    new Thread(new ClientHandler(clientSocket, broadcaster)).start();
    		}
    		System.out.println("2 clientes conectaram");
    		

    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
	
	public void notifyPlayers(String hostName, String clientName) {
	    for (Socket clientSocket : clients) {
	        try {
	            ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
	            output.writeObject(new PlayerNames(hostName, clientName)); // Envia os nomes dos jogadores
	            output.flush();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}

}
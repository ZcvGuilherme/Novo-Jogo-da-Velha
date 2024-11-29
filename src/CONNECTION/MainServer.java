package CONNECTION;

import java.io.*;
import java.net.*;
import java.util.*;

public class MainServer {
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
}

package CONNECTION;

import GAME.STATUS.GameStatus;
import java.io.*;
import java.net.*;
import java.util.*;

public class Broadcaster {
    private final List<Socket> clients;

    public Broadcaster(List<Socket> clients) {
        this.clients = clients;
    }

    // Método para enviar o GameStatus para todos os clientes conectados
    public void broadcast(GameStatus status) {
        synchronized (clients) {
            for (Socket client : clients) {
                try {
                    ObjectOutputStream clientOutput = new ObjectOutputStream(client.getOutputStream());
                    clientOutput.writeObject(status); // Envia o GameStatus para os outros clientes
                    clientOutput.flush();
                } catch (IOException e) {
                    System.out.println("Erro ao enviar para cliente: " + client.getInetAddress());
                    e.printStackTrace();
                }
            }
        }
    }
}

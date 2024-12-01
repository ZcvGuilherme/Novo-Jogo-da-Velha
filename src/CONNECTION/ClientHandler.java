package CONNECTION;

import GAME.STATUS.GameStatus;
import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private final Broadcaster broadcaster;

    public ClientHandler(Socket socket, Broadcaster broadcaster) {
        this.socket = socket;
        this.broadcaster = broadcaster;
    }

    @Override
    public void run() {
        try (ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
            Object receivedObject;
            while ((receivedObject = input.readObject()) != null) {
                if (receivedObject instanceof GameStatus) {
                    GameStatus status = (GameStatus) receivedObject;
                    System.out.println("GameStatus recebido do cliente: " + status);
                    broadcaster.broadcast(status); // Transmite para os outros clientes
                } else {
                    System.out.println("Objeto não reconhecido recebido: " + receivedObject);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Cliente desconectado: " + socket.getInetAddress());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

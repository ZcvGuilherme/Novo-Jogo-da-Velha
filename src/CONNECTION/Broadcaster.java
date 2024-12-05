
package CONNECTION;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import GAME.STATUS.GameStatus;

public class Broadcaster {
    private final List<Socket> clients;
    private final Map<Socket, ObjectOutputStream> clientStreams = new HashMap<>();

    public Broadcaster(List<Socket> clients) {
        this.clients = clients;
    }

    public void addClient(Socket client) throws IOException {
        synchronized (clients) {
            clients.add(client);
            clientStreams.put(client, new ObjectOutputStream(client.getOutputStream()));
        }
    }

    public void broadcast(GameStatus status) {
        synchronized (clients) {
            Iterator<Socket> iterator = clients.iterator();
            while (iterator.hasNext()) {
                Socket client = iterator.next();
                try {
                    clientStreams.get(client).writeObject(status);
                    clientStreams.get(client).flush();
                    System.out.println("Enviando para todos os clientes....");
                } catch (IOException e) {
                    System.out.println("Erro ao enviar para cliente: " + client.getInetAddress());
                    iterator.remove();
                    clientStreams.remove(client);
                }
            }
        }
    }
}

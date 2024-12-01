	package CONNECTION.TESTES;
	
	import java.io.*;
	import java.net.*;
	import java.util.*;
	
	public class ClientHandler implements Runnable {
	    private final Socket socket;
	    private final List<Socket> clients;
	
	    public ClientHandler(Socket socket, List<Socket> clients) {
	        this.socket = socket;
	        this.clients = clients;
	    }
	
	    @Override
	    public void run() {
	        try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	             PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {
	
	            String message;
	            while ((message = input.readLine()) != null) {
	                System.out.println("Mensagem recebida: " + message);
	                broadcast(message);
	            }
	        } catch (IOException e) {
	            System.out.println("Cliente desconectado: " + socket.getInetAddress());
	        } finally {
	            try {
	                socket.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	
	    private void broadcast(String message) {
	        synchronized (clients) {
	            for (Socket client : clients) {
	                try {
	                    PrintWriter clientOutput = new PrintWriter(client.getOutputStream(), true);
	                    clientOutput.println(message);
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	}

package CONNECTION;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import GAME.STATUS.GameStatus;
import GUI.OBSERVERS.Observable;
import GUI.OBSERVERS.Observer;

public class Client implements Observer, Observable{
	private List<Observer> observers;
	private Socket socket;
	private ObjectOutputStream output;
    private ObjectInputStream input;
    private GameStatus status;
    
    Client(){
    	this.observers = new ArrayList<>();
    }
	public void connect(String host, int porta) {
		try {
			socket = new Socket(host, porta);
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());
				
			System.out.println("Conectado ao servidor: "+ host + ":" + porta);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void update(GameStatus status) {
		if (socket != null && !socket.isClosed()) {
			try {
				output.writeObject(status);
				output.flush();
				System.out.println("Status enviado!");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void waitUpdate() {
		new Thread(() -> {
				try {
					while(true) {
						Object objetoRecebido = input.readObject();
						if (objetoRecebido instanceof GameStatus) {
							this.status = (GameStatus) objetoRecebido;
							System.out.println("GameStatus recebido");
							System.out.println("Atualizando Tela...");
							notifyObservers();
						}
					}
				}  catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
		}).start();
	}
	
	public void desconectar() {
	    try {
	        if (output != null) output.close();
	        if (input != null) input.close();
	        if (socket != null) socket.close();
	        System.out.println("Client desconectado");
	    } catch (IOException e) {
	        System.out.println("Erro ao desconectar: " + e.getMessage());
	    }
	}

	public void addObserver(Observer observer) {
	    if (!observers.contains(observer)) {
	        observers.add(observer);
	    }
	}
	
	public void removeObserver(Observer observer) {
		this.observers.remove(observer);
	}

	public void notifyObservers() {
		if (status != null) {
			for (Observer observer : observers) {
				observer.update(status);
			}
		}
		
	
	}

}

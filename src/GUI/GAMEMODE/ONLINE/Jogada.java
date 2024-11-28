package GUI.GAMEMODE.ONLINE;

import java.io.Serializable;

public class Jogada implements Serializable{
	int i;
	int j;
	char simbolo;
	public Jogada(int i, int j, char simbolo) {
		this.i = i;
		this.j = j;
		this.simbolo = simbolo;
	}
	public int getI() {
		return i;
	}
	
	public int getJ() {
		return j;
	}
	
	public char getSimbolo() {
		return simbolo;
	}
	
}

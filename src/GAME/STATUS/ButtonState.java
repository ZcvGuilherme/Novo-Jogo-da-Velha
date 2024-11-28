package GAME.STATUS;

public class ButtonState {
	private int i;
	private int j;
	private boolean clicado;
	private char simbolo;
	public ButtonState(boolean clicado, char k, int i, int j) {
		this.clicado = clicado;
		this.simbolo = k;
		this.i = i;
		this.j = j;
	}
	public boolean isClicado() {
		return clicado;
	}
	public void setClicado(boolean clicado) {
		this.clicado = clicado;
	}
	public char getSimbolo() {
		return simbolo;
	}
	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}
	public int getI() {
		return i;
	}
	public int getJ() {
		return j;
	}

}

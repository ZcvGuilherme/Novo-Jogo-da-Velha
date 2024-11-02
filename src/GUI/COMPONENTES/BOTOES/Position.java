package GUI.COMPONENTES.BOTOES;

public enum Position {
	A0(0, 0), A1(0, 1), A2(0, 2),
    B0(1, 0), B1(1, 1), B2(1, 2),
    C0(2, 0), C1(2, 1), C2(2, 2);
	
	private final int linha, coluna;
	
	Position(int linha, int coluna){
		this.linha = linha;
		this.coluna = coluna;
	}
	public int getLinha() {
		return linha;
	}
	public int getColuna() {
		return coluna;
	}
}

package modelo;

public class Tabuleiro {

	private Unidade matriz[][];

	public Tabuleiro(int tam) {
		this.matriz = new Unidade[tam][tam];
	}

	public Unidade[][] getMatriz() {
		return matriz;
	}

	public void setMatriz(Unidade[][] matriz) {
		this.matriz = matriz;
	}
	
	
	
	
}

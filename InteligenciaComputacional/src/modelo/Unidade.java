package modelo;

public class Unidade {
		
	private int inf;
	private int linha;
	private int coluna;
	
	public Unidade(int inf) {
		this.inf = inf;
	}
	
	public Unidade(int inf, int linha, int coluna) {
		this.inf = inf;
		this.linha = linha;
		this.coluna = coluna;
	}
	
	public int getInf() {
		return inf;
	}
	public void setInf(int inf) {
		this.inf = inf;
	}
	public int getLinha() {
		return linha;
	}
	public void setLinha(int linha) {
		this.linha = linha;
	}
	public int getColuna() {
		return coluna;
	}
	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	
	
}

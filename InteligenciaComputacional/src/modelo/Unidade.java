package modelo;

public class Unidade {
		
	private String inf;
	private int linha;
	private int coluna;
	
	public Unidade(String inf) {
		this.inf = inf;
	}
	
	public Unidade(String inf, int linha, int coluna) {
		this.inf = inf;
		this.linha = linha;
		this.coluna = coluna;
	}
	
	public String getInf() {
		return inf;
	}
	public void setInf(String inf) {
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

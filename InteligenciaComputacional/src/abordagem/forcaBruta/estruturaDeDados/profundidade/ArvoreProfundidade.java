package abordagem.forcaBruta.estruturaDeDados.profundidade;

import modelo.Tabuleiro;

public class ArvoreProfundidade {

	private boolean visitado;
	private Tabuleiro tabuleiro;
	
	public ArvoreProfundidade(Tabuleiro tabuleiro, boolean status){
		this.tabuleiro = tabuleiro;
		visitado = status;
	}

	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

}

package modelo;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {

	private Unidade matriz[][];
	private int ordem;
	
	public Tabuleiro(int tam) {
		this.ordem = tam;
		this.matriz = new Unidade[tam][tam];
	}

	public Unidade[][] getMatriz() {
		return matriz;
	}

	public void setMatriz(Unidade[][] matriz) {
		this.matriz = matriz;
	}
	/**
	 * Analisa se o tabuleiro é otimo 
	 * @param tabuleiro
	 * @return
	 */
	public boolean analisaOtimo(Tabuleiro tabuleiro){
		
		for(int i = 0; i < tabuleiro.getOrdem(); i++){
			for(int j = 0; j < tabuleiro.getOrdem(); j++){
				if(tabuleiro.getMatriz()[i][j].getLinha()!=i || tabuleiro.getMatriz()[i][j].getColuna()!=j){
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * Lista todos os filhos de uma determinado pai
	 * @param pai
	 * @return
	 */
	public List<Tabuleiro> gerarFilhos(Tabuleiro pai){
		ArrayList<Tabuleiro> filhos = new ArrayList<>();
		
		Tabuleiro filho = new Tabuleiro(this.ordem);
		filho.setMatriz(this.matriz);
		
		int linhaBranco = 0, colunaBranco = 0;
		
		//procura unidade branco
		for(int i = 0; i < this.ordem; i++){
			for(int j = 0; j < this.ordem; j++){
				if(this.matriz[i][j].getInf().equals("branco")){
					linhaBranco = i;
					colunaBranco = j;
					break;
				}
			}
			if(this.matriz[linhaBranco][colunaBranco].getInf().equals("branco")){
				break;
			}
		}
		
		if((linhaBranco - 1) >= 0 ){
			Unidade aux = filho.matriz[linhaBranco - 1][colunaBranco];
			filho.matriz[linhaBranco - 1][colunaBranco] = filho.matriz[linhaBranco][colunaBranco];
			filho.matriz[linhaBranco][colunaBranco] = aux;
			
			filhos.add(filho);
			
			filho = new Tabuleiro(this.ordem);
			filho.setMatriz(this.matriz);
			
		}
		
		if((linhaBranco + 1) < this.ordem ){
			Unidade aux = filho.matriz[linhaBranco + 1][colunaBranco];
			filho.matriz[linhaBranco + 1][colunaBranco] = filho.matriz[linhaBranco][colunaBranco];
			filho.matriz[linhaBranco][colunaBranco] = aux;
			
			filhos.add(filho);
			filho = new Tabuleiro(this.ordem);
			filho.setMatriz(this.matriz);
		}
		if((colunaBranco - 1) >= 0 ){
			Unidade aux = filho.matriz[linhaBranco][colunaBranco - 1];
			filho.matriz[linhaBranco][colunaBranco - 1] = filho.matriz[linhaBranco][colunaBranco];
			filho.matriz[linhaBranco][colunaBranco] = aux;
			
			filhos.add(filho);
			filho = new Tabuleiro(this.ordem);
			filho.setMatriz(this.matriz);
		}
		if((linhaBranco + 1) < this.ordem ){
			Unidade aux = filho.matriz[linhaBranco][colunaBranco + 1];
			filho.matriz[linhaBranco][colunaBranco + 1] = filho.matriz[linhaBranco][colunaBranco];
			filho.matriz[linhaBranco][colunaBranco] = aux;
			
			filhos.add(filho);
			
		}
		
		return filhos;
	}
	
	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}
}

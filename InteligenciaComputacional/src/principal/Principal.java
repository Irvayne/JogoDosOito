package principal;

import abordagem.forcaBruta.estruturaDeDados.Arvore;
import modelo.Tabuleiro;
import modelo.Unidade;

public class Principal {

	

	public static void main(String[] args) {
		Unidade matriz[][] = new Unidade[3][3];
		matriz[0][0] = new Unidade(-1);
		matriz[0][1] = new Unidade(1);
		matriz[0][2] = new Unidade(2);
		matriz[1][0] = new Unidade(3);
		matriz[1][1] = new Unidade(4);
		matriz[1][2] = new Unidade(5);
		matriz[2][0] = new Unidade(6);
		matriz[2][1] = new Unidade(7);
		matriz[2][2] = new Unidade(8);
		
		Tabuleiro tabuleiro = new Tabuleiro(3, matriz);
		System.out.println("Nao ordenado");
		for(int i = 0; i < tabuleiro.getOrdem(); i++){
			for(int j = 0; j < tabuleiro.getOrdem(); j++){
				System.out.print(tabuleiro.getMatriz()[i][j].getInf()+" ");
			}
			System.out.println();
			}
		Arvore arvore = new Arvore(tabuleiro,22);
		//Tabuleiro ordenado = arvore.buscaProfundidade(null);
		Tabuleiro ordenado = arvore.buscaEmLargura(null);
		System.out.println("Ordenado"); 
		for(int i = 0; i < ordenado.getOrdem(); i++){
			for(int j = 0; j < ordenado.getOrdem(); j++){
				System.out.print(ordenado.getMatriz()[i][j].getInf()+" ");
			}
			System.out.println();
			}
	}

}

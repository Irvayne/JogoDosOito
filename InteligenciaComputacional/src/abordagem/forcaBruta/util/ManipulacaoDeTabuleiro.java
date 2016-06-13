package abordagem.forcaBruta.util;

import java.util.ArrayList;
import java.util.List;

import modelo.Tabuleiro;
import modelo.Unidade;

public class ManipulacaoDeTabuleiro {

	public static ArrayList<Tabuleiro> removerIgual(Tabuleiro avo, List<Tabuleiro> filhos) {
		if (avo == null)
			return (ArrayList<Tabuleiro>) filhos;
		ArrayList<Tabuleiro> lista = new ArrayList<>();
		for (Tabuleiro aux : filhos) {
			if (comparaTabuleiro(avo, aux) == false)
				lista.add(aux);
		}
		return lista;
	}

	private static boolean comparaTabuleiro(Tabuleiro x, Tabuleiro y) {
		for (int l = 0; l < x.getOrdem(); l++) {
			for (int c = 0; c < x.getOrdem(); c++) {
				if (x.getMatriz()[l][c].getInf() != y.getMatriz()[l][c].getInf()) {
					return false;
				}
			}
		}
		return true;
	}

	public static ArrayList<Tabuleiro> gerarFilhos(Tabuleiro pai) {
		ArrayList<Tabuleiro> filhos = new ArrayList<>();

		int linhaBranco = 0, colunaBranco = 0;

		// procura unidade branco
		for (int i = 0; i < pai.getOrdem(); i++) {
			for (int j = 0; j < pai.getOrdem(); j++) {
				if (pai.getMatriz()[i][j].getInf() == -1) {
					linhaBranco = i;
					colunaBranco = j;
					break;
				}
			}
			if (pai.getMatriz()[linhaBranco][colunaBranco].getInf() == -1) {
				break;
			}
		}

		if ((linhaBranco - 1) >= 0) {

			Tabuleiro filho1 = new Tabuleiro(pai.getOrdem(), pai.getMatriz(),pai);
			Unidade aux = filho1.getMatriz()[linhaBranco - 1][colunaBranco];
			filho1.getMatriz()[linhaBranco - 1][colunaBranco] = filho1.getMatriz()[linhaBranco][colunaBranco];
			filho1.getMatriz()[linhaBranco][colunaBranco] = aux;
		
			filhos.add(filho1);

		}

		if ((linhaBranco + 1) < pai.getOrdem()) {
			Tabuleiro filho2 = new Tabuleiro(pai.getOrdem(), pai.getMatriz(),pai);
			Unidade aux = filho2.getMatriz()[linhaBranco + 1][colunaBranco];
			filho2.getMatriz()[linhaBranco + 1][colunaBranco] = filho2.getMatriz()[linhaBranco][colunaBranco];
			filho2.getMatriz()[linhaBranco][colunaBranco] = aux;
			
			
			filhos.add(filho2);

		}
		if ((colunaBranco - 1) >= 0) {
			Tabuleiro filho3 = new Tabuleiro(pai.getOrdem(), pai.getMatriz(), pai);
			Unidade aux = filho3.getMatriz()[linhaBranco][colunaBranco - 1];
			filho3.getMatriz()[linhaBranco][colunaBranco - 1] = filho3.getMatriz()[linhaBranco][colunaBranco];
			filho3.getMatriz()[linhaBranco][colunaBranco] = aux;
			
			
			filhos.add(filho3);

		}
		if ((colunaBranco + 1) < pai.getOrdem()) {
			Tabuleiro filho4 = new Tabuleiro(pai.getOrdem(), pai.getMatriz(), pai);
			Unidade aux = filho4.getMatriz()[linhaBranco][colunaBranco + 1];
			filho4.getMatriz()[linhaBranco][colunaBranco + 1] = filho4.getMatriz()[linhaBranco][colunaBranco];
			filho4.getMatriz()[linhaBranco][colunaBranco] = aux;
			
			
			filhos.add(filho4);

		}

		return filhos;
	}

}

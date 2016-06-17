package abordagem.forcaBruta.estruturaDeDados;

import java.awt.TextArea;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import abordagem.forcaBruta.util.ManipulacaoDeTabuleiro;
import modelo.NosExpandidos;
import modelo.Tabuleiro;

public class Largura {

	public static ArrayList<Tabuleiro> buscaEmLargura(Tabuleiro raiz, int altura, TextArea text, NosExpandidos nosExpandidos) {

		Queue<Tabuleiro> fila = new LinkedList<>();
		fila.add(raiz);

		text.setText(text.getText() + "----------------------------------------------Nível " + 1
				+ "----------------------------------------------\n");
		Tabuleiro pai = fila.poll();
		nosExpandidos.qnt++;
		for (int i = 0; i < raiz.getOrdem(); i++) {
			for (int j = 0; j < raiz.getOrdem(); j++) {
				text.setText(text.getText() + "\t" + raiz.getMatriz()[i][j].getInf());
			}
			text.setText(text.getText() + "\n");
		}

		text.setText(text.getText() + "\n");

		ArrayList<Tabuleiro> filhos = ManipulacaoDeTabuleiro.removerIgual(pai.getPai(),
				ManipulacaoDeTabuleiro.gerarFilhos(pai));
		for (Tabuleiro aux : filhos) {

			for (int l = 0; l < aux.getOrdem(); l++) {
				for (int c = 0; c < aux.getOrdem(); c++) {
					if (c == 0) {
						text.setText(text.getText() + "\t\t" + aux.getMatriz()[l][c].getInf());
					} else {
						text.setText(text.getText() + "\t" + aux.getMatriz()[l][c].getInf());
					}
				}

				text.setText(text.getText() + "\n");
			}

			if (aux.analisaOtimo())
				return retornaLista(aux);
			fila.add(aux);

			text.setText(text.getText() + "\n");
		}

		Tabuleiro paiNew = null;
		for (int nivel = 1; nivel < altura; nivel++) {

			text.setText(text.getText() + "----------------------------------------------Nível " + nivel+1
					+ "----------------------------------------------");
			text.setText(text.getText() + "\n");

			int tam = fila.size();

			for (int qnt = 0; qnt < tam; qnt++) {
				paiNew = fila.poll();
				nosExpandidos.qnt++;

				for (int i = 0; i < paiNew.getOrdem(); i++) {
					for (int j = 0; j < paiNew.getOrdem(); j++) {
						text.setText(text.getText() + "\t" + paiNew.getMatriz()[i][j].getInf());
					}
					text.setText(text.getText() + "\n");
				}

				text.setText(text.getText() + "\n");

				ArrayList<Tabuleiro> filhosNew = ManipulacaoDeTabuleiro.removerIgual(paiNew.getPai(),
						ManipulacaoDeTabuleiro.gerarFilhos(paiNew));
				for (Tabuleiro aux : filhosNew) {

					for (int l = 0; l < aux.getOrdem(); l++) {
						for (int c = 0; c < aux.getOrdem(); c++) {
							if (c == 0) {
								text.setText(text.getText() + "\t\t" + aux.getMatriz()[l][c].getInf());
							} else {
								text.setText(text.getText() + "\t" + aux.getMatriz()[l][c].getInf());
							}
						}

						text.setText(text.getText() + "\n");
					}

					text.setText(text.getText() + "\n");

					if (aux.analisaOtimo())
						return retornaLista(aux);
					fila.add(aux);
				}
			}

		}
		return null;
	}

	private static ArrayList<Tabuleiro> retornaLista(Tabuleiro solucao) {

		ArrayList<Tabuleiro> nos = new ArrayList<>();
		nos.add(solucao);
		while (solucao.getPai() != null) {
			solucao = solucao.getPai();
			nos.add(solucao);
		}

		ArrayList<Tabuleiro> resultado = new ArrayList<>();
		for (int i = nos.size() - 1; i >= 0; i--) {
			resultado.add(nos.get(i));
		}

		return resultado;
	}

}

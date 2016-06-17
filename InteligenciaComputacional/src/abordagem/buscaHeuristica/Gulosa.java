package abordagem.buscaHeuristica;

import java.awt.TextArea;
import java.util.ArrayList;
import java.util.List;

import abordagem.forcaBruta.util.ManipulacaoDeTabuleiro;
import modelo.NosExpandidos;
import modelo.Tabuleiro;

public class Gulosa {
	// tabuleiro inicial
	/**
	 * metodo busca gulosa retorna lista de expansões
	 * 
	 * @return
	 */
	public static ArrayList<Tabuleiro> buscaGulosa(Tabuleiro raiz, int altura, TextArea text, NosExpandidos nosExpandidos ) {

		text.setText(text.getText() + "----------------------------------------------Nível " + 1
				+ "----------------------------------------------\n");
		
		
		ArrayList<Tabuleiro> filhos = new ArrayList<Tabuleiro>();

		ArrayList<Tabuleiro> nos = new ArrayList<Tabuleiro>();
		int aux = 0;

		nos.add(raiz);
		aux++;

		while (!raiz.analisaOtimo() && aux < altura) {
			nosExpandidos.qnt++;
			for (int i = 0; i < raiz.getOrdem(); i++) {
				for (int j = 0; j < raiz.getOrdem(); j++) {
					text.setText(text.getText() + "\t" + raiz.getMatriz()[i][j].getInf());
				}
				text.setText(text.getText() + "\n");
			}

			text.setText(text.getText() + "\n");

			filhos = ManipulacaoDeTabuleiro.gerarFilhos(raiz);
			for (Tabuleiro tabuleiro : nos) {
				filhos = ManipulacaoDeTabuleiro.removerIgual(tabuleiro, filhos);
			}

			raiz = otimoFilhos(filhos, text);

			nos.add(raiz);
			aux++;

			text.setText(text.getText() + "----------------------------------------------Nível " + aux
					+ "----------------------------------------------");
			text.setText(text.getText() + "\n");
		}
		return retornaLista(raiz);
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

		// for(Tabuleiro aux:resultado){
		// for (int i = 0; i < 3; i++) {
		// for (int j = 0; j < 3; j++) {
		// System.out.print(aux.getMatriz()[i][j].getInf());
		// }
		// System.out.println();
		// }
		// System.out.println();
		// }

		return resultado;
	}

	/**
	 * Acha o melhor uma lista de filhos
	 * 
	 * @param filhos
	 * @return
	 */
	public static Tabuleiro otimoFilhos(List<Tabuleiro> filhos, TextArea text) {
		int menor = 1000;
		int aux = 0;

		for (int i = 0; i < filhos.size(); i++) {
			int custo = 0;
			for (int j = 0; j < filhos.get(i).getOrdem(); j++) {
				for (int p = 0; p < filhos.get(i).getOrdem(); p++) {
					if (filhos.get(i).getMatriz()[j][p].getInf() != -1) {
						int custoLinha, custoColuna;
						custoLinha = Math.abs(j - filhos.get(i).getMatriz()[j][p].getLinha());
						custoColuna = Math.abs(p - filhos.get(i).getMatriz()[j][p].getColuna());
						custo = custo + (custoLinha + custoColuna);
					}
				}
			}
			if (custo < menor) {
				menor = custo;
				aux = i;
			}

			for (int l = 0; l < filhos.get(i).getOrdem(); l++) {
				for (int c = 0; c < filhos.get(i).getOrdem(); c++) {
					if (c == 0) {
						text.setText(text.getText() + "\t\t" + filhos.get(i).getMatriz()[l][c].getInf());
					} else {
						text.setText(text.getText() + "\t" + filhos.get(i).getMatriz()[l][c].getInf());
					}
				}
				if (l == 1) {
					text.setText(text.getText() + "\t (Custo = " + custo + ")");
				}
				text.setText(text.getText() + "\n");
			}

			text.setText(text.getText() + "\n");

		}
		return filhos.get(aux);
	}

}
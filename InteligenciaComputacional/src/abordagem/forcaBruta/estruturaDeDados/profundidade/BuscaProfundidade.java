package abordagem.forcaBruta.estruturaDeDados.profundidade;

import java.awt.TextArea;
import java.util.ArrayList;
import java.util.List;

import abordagem.forcaBruta.util.ManipulacaoDeTabuleiro;
import modelo.NosExpandidos;
import modelo.Tabuleiro;

public class BuscaProfundidade {

	static private int ordem;

	static public ArrayList<Tabuleiro> buscaProfunidade(Tabuleiro raiz, int altura, TextArea text, NosExpandidos nosQnt) {
		ordem = raiz.getMatriz().length;

		ArvoreProfundidade arvoreProfundidade = new ArvoreProfundidade(raiz, false);
		ArrayList<ArvoreProfundidade> nos = new ArrayList<ArvoreProfundidade>();
		ArrayList<Tabuleiro> resultado = new ArrayList<Tabuleiro>();
		int nivel = 0;

		nos.add(arvoreProfundidade);
		nivel++;
		nosQnt.qnt++;
		
		
		while (!arvoreProfundidade.getTabuleiro().analisaOtimo()) {

			if (!arvoreProfundidade.isVisitado()) {

				for (int i = 0; i < ordem; i++) {
					for (int j = 0; j < ordem; j++) {
						text.setText(
								text.getText() + "\t" + arvoreProfundidade.getTabuleiro().getMatriz()[i][j].getInf());
					}
					text.setText(text.getText() + "\n");
				}

				text.setText(text.getText() + "\n");

				nos.get(nos.size() - 1).setVisitado(true);

				List<Tabuleiro> filhos = new ArrayList<Tabuleiro>();
				filhos = ManipulacaoDeTabuleiro.gerarFilhos(arvoreProfundidade.getTabuleiro());
				for (ArvoreProfundidade profundidade : nos) {
					filhos = ManipulacaoDeTabuleiro.removerIgual(profundidade.getTabuleiro(), filhos);

				}

				List<ArvoreProfundidade> filhosProfundidade = new ArrayList<ArvoreProfundidade>();
				for (Tabuleiro filho : filhos) {
					ArvoreProfundidade aux = new ArvoreProfundidade(filho, false);
					filhosProfundidade.add(aux);
				}

				if (nivel < altura) {
					for (Tabuleiro profundidade : filhos) {

						if (profundidade.analisaOtimo())
							return resultado;

						for (int l = 0; l < ordem; l++) {
							for (int c = 0; c < ordem; c++) {
								if (c == 0) {
									text.setText(text.getText() + "\t\t" + profundidade.getMatriz()[l][c].getInf());
								} else {
									text.setText(text.getText() + "\t" + profundidade.getMatriz()[l][c].getInf());
								}
							}

							text.setText(text.getText() + "\n");
						}

						text.setText(text.getText() + "\n");

					}
					
					nos.addAll(filhosProfundidade);
					nosQnt.qnt = nosQnt.qnt + filhosProfundidade.size();
					arvoreProfundidade = nos.get(nos.size() - 1);
					nivel++;

					text.setText(text.getText() + "----------------------------------------------Nível " + nivel
							+ "----------------------------------------------");
					text.setText(text.getText() + "\n");

				} else {
					if (nos.size() > 1) {
						nos.remove(nos.size() - 1);
						arvoreProfundidade = nos.get(nos.size() - 1);
					}
				}

			} else {
				nos.remove(nos.size() - 1);
				if (!nos.isEmpty()) {
					arvoreProfundidade = nos.get(nos.size() - 1);
					nivel--;
				} else {
					break;
				}
			}

		}

		for (ArvoreProfundidade arvore : nos) {
			if (arvore.getTabuleiro().analisaOtimo())
				return resultado;
			resultado.add(arvore.getTabuleiro());
		}

		return resultado;

	}

}

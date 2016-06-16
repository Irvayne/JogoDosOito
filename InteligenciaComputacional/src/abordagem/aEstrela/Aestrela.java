package abordagem.aEstrela;


import java.util.ArrayList;
import java.util.List;

import abordagem.forcaBruta.util.ManipulacaoDeTabuleiro;
import modelo.Tabuleiro;

public class Aestrela {

	public static void buscaAEstrela(Tabuleiro raiz, int altura){
		raiz.setCustoGeracao(0);
		
		ArrayList<Tabuleiro> nosEspandidos = new ArrayList<>();
		ArrayList<Tabuleiro> nosFolhas = new ArrayList<>();
		
		nosEspandidos.add(raiz);
		
		while(!raiz.analisaOtimo()){
		
			for (int i = 0; i < raiz.getMatriz().length; i++) {
				for (int j = 0; j < raiz.getMatriz().length; j++) {
					System.out.print(raiz.getMatriz()[i][j].getInf());
					
				}
				System.out.println();
			}
			
			raiz.setVisitado(true);
			
			ArrayList<Tabuleiro> aux = ManipulacaoDeTabuleiro.gerarFilhos(raiz);
			
			for (Tabuleiro tabuleiro : nosFolhas) {
				aux = ManipulacaoDeTabuleiro.removerIgual(tabuleiro, aux);
			}
			
			for (Tabuleiro tabuleiro : nosEspandidos) {
				aux = ManipulacaoDeTabuleiro.removerIgual(tabuleiro, aux);
			}
			
			for (Tabuleiro tabuleiro : aux) {
				tabuleiro.setCustoGeracao(raiz.getCustoGeracao()+1);
			}
			
			nosFolhas.addAll(aux);

			
			raiz = otimoFilhos(nosFolhas);
			
			nosFolhas.remove(nosFolhas.indexOf(raiz));
			
			nosEspandidos.add(raiz);
			
			
		}
		
		
	}
	
	public static Tabuleiro otimoFilhos(List<Tabuleiro> filhos){
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
			custo = custo + filhos.get(i).getCustoGeracao();
			
			
			System.out.println("Custo = "+custo);
			for (int k = 0; k < 3; k++) {
				for (int j = 0; j < 3; j++) {
					System.out.print("\t"+filhos.get(i).getMatriz()[k][j].getInf());
					
				}
				System.out.println();
			}
			System.out.println();
			if (custo < menor) {
				menor = custo;
				aux = i;
			}
			

		}
		return filhos.get(aux);
	}
}

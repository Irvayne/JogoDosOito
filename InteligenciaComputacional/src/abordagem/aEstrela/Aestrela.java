package abordagem.aEstrela;


import java.awt.TextArea;
import java.util.ArrayList;
import java.util.List;

import abordagem.forcaBruta.util.ManipulacaoDeTabuleiro;
import modelo.Tabuleiro;

public class Aestrela {

	public static ArrayList<Tabuleiro> buscaAEstrela(Tabuleiro raiz, int altura, TextArea text){
		raiz.setCustoGeracao(0);
		
		ArrayList<Tabuleiro> nosEspandidos = new ArrayList<>();
		ArrayList<Tabuleiro> nosFolhas = new ArrayList<>();
		
		nosEspandidos.add(raiz);
		
		while(!raiz.analisaOtimo()){
		
//			for (int i = 0; i < raiz.getMatriz().length; i++) {
//				for (int j = 0; j < raiz.getMatriz().length; j++) {
//					System.out.print(raiz.getMatriz()[i][j].getInf());
//					
//				}
//				System.out.println();
//			}
			
			for (int i = 0; i < raiz.getOrdem(); i++) {
				for (int j = 0; j < raiz.getOrdem(); j++) {
					text.setText(text.getText()+"\t"+raiz.getMatriz()[i][j].getInf());
				}
				text.setText(text.getText()+"\n");
			}
			
			text.setText(text.getText()+"\n");
			
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

			
			raiz = otimoFilhos(nosFolhas, text);
			
			nosFolhas.remove(nosFolhas.indexOf(raiz));
			
			nosEspandidos.add(raiz);
			
			
		}
		
		return retornaLista(raiz);
		
	}
	
private static ArrayList<Tabuleiro> retornaLista(Tabuleiro solucao){
		
		ArrayList<Tabuleiro> nos = new ArrayList<>();
		nos.add(solucao);
		while(solucao.getPai()!=null){
			solucao = solucao.getPai();
			nos.add(solucao);
		}
		
		ArrayList<Tabuleiro> resultado = new ArrayList<>();
		for (int i = nos.size()-1; i >= 0; i--) {
			resultado.add(nos.get(i));
		}
		
		for(Tabuleiro aux:resultado){
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					System.out.print(aux.getMatriz()[i][j].getInf());
				}
				System.out.println();
			}
			System.out.println();
		}
		
		return resultado;
	}
	
	public static Tabuleiro otimoFilhos(List<Tabuleiro> filhos, TextArea text){
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
			
			
//			System.out.println("Custo = "+custo);
//			for (int k = 0; k < 3; k++) {
//				for (int j = 0; j < 3; j++) {
//					System.out.print("\t"+filhos.get(i).getMatriz()[k][j].getInf());
//					
//				}
//				System.out.println();
//			}
//			System.out.println();
			if (custo < menor) {
				menor = custo;
				aux = i;
			}
			
			for (int l = 0; l < filhos.get(i).getOrdem(); l++) {
				for (int c = 0; c < filhos.get(i).getOrdem(); c++) {
					if(c == 0){
						text.setText(text.getText()+"\t\t"+filhos.get(i).getMatriz()[l][c].getInf());
					}else{
						text.setText(text.getText()+"\t"+filhos.get(i).getMatriz()[l][c].getInf());
					}
				}
				if(l == 1){
					text.setText(text.getText()+"\t (Custo = "+ custo + ")");
				}
				text.setText(text.getText()+"\n");
			}
			
			text.setText(text.getText()+"\n");

		}
		return filhos.get(aux);
	}
}

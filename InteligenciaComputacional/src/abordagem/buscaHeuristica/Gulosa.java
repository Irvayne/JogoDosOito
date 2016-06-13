package abordagem.buscaHeuristica;
import java.util.ArrayList;
import java.util.List;
import abordagem.forcaBruta.util.ManipulacaoDeTabuleiro;
import modelo.Tabuleiro;
public class Gulosa {
	//tabuleiro inicial
	/**
	 * metodo busca gulosa retorna lista de expansões
	 * @return
	 */
	public static List<Tabuleiro> buscaGulosa(Tabuleiro raiz, int altura){
		//guarda os nos filhos
		List<Tabuleiro> filhos = new ArrayList<Tabuleiro>();
		//guarda os nos pais
		List<Tabuleiro> nos = new ArrayList<Tabuleiro>();
		int aux = 0;
		
		//adiciona a raiz e testa se é o ótimo
		nos.add(raiz);
		aux++;
		//enquando a raiz não for a otima
		while (!raiz.analisaOtimo() && aux < altura) {
			System.out.println("-------------------------------------");
			for(int i = 0; i < raiz.getOrdem(); i++){
				for(int j = 0; j < raiz.getOrdem(); j++){
					System.out.print(raiz.getMatriz()[i][j].getInf()+" ");
				}
				System.out.println();
			}
			System.out.println();
			//gera os filhos da raiz
			filhos = ManipulacaoDeTabuleiro.gerarFilhos(raiz);
			for (Tabuleiro tabuleiro : nos) {
				filhos = ManipulacaoDeTabuleiro.removerIgual(tabuleiro, filhos);
			}
			for (Tabuleiro tabuleiro : filhos) {
				for (int i = 0; i < tabuleiro.getOrdem(); i++) {
					for (int j = 0; j < tabuleiro.getOrdem(); j++) {
						System.out.print(tabuleiro.getMatriz()[i][j].getInf()+" ");
					}
					System.out.println();
				}
				System.out.println();
			}
			System.out.println("---------------------------------------");
			//raiz recebe o otimo
			raiz = otimoFilhos(filhos);
			//adiciona a raiz
			nos.add(raiz);
			aux++;
		}
		return nos;
	}
	/**
	 * Acha o melhor uma lista de filhos
	 * @param filhos
	 * @return
	 */
	public static Tabuleiro otimoFilhos(List<Tabuleiro> filhos){
		//		//preenche o vetor custos
		//		for (Tabuleiro tabuleiro : filhos) {
		//
		//			//percorre cada filho
		//			for (int i = 0; i < tabuleiro.getOrdem(); i++) {
		//				for (int j = 0; j < tabuleiro.getOrdem(); j++) {
		//					if (tabuleiro.getMatriz()[i][j].getInf() != -1) {
		//						if (tabuleiro.getMatriz()[i][j].getInf() == mascara[i][j]) {
		//							tabuleiro.getMatriz()[i][j].setColuna(0);
		//							tabuleiro.getMatriz()[i][j].setLinha(0);
		//
		//						}else {	
		//							if (tabuleiro.getMatriz()[i][j].getInf()%tabuleiro.getOrdem() == 0 && 
		//									tabuleiro.getMatriz()[i][j].getInf() >= tabuleiro.getOrdem()) {
		//								tabuleiro.getMatriz()[i][j].setColuna(tabuleiro.getOrdem() - 1);
		//								tabuleiro.getMatriz()[i][j].setLinha((tabuleiro.getMatriz()[i][j].getInf()/tabuleiro.getOrdem()) - 1);
		//							}else {
		//								int aux = tabuleiro.getMatriz()[i][j].getInf()/tabuleiro.getOrdem();
		//								tabuleiro.getMatriz()[i][j].setColuna((tabuleiro.getMatriz()[i][j].getInf()%tabuleiro.getOrdem()) - 1);
		//								tabuleiro.getMatriz()[i][j].setLinha(aux);
		//							}
		//
		//						}
		//					}
		//				}
		//			}
		//
		//		}
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
		}
		return filhos.get(aux);
	}
	
}
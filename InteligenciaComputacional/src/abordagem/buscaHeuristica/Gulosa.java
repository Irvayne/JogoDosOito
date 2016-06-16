package abordagem.buscaHeuristica;
import java.awt.TextArea;
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
	public static List<Tabuleiro> buscaGulosa(Tabuleiro raiz, int altura, TextArea text){
		
		List<Tabuleiro> filhos = new ArrayList<Tabuleiro>();
		
		List<Tabuleiro> nos = new ArrayList<Tabuleiro>();
		int aux = 0;
		
		
		nos.add(raiz);
		aux++;
		
		while (!raiz.analisaOtimo() && aux < altura) {
			
			for (int i = 0; i < raiz.getOrdem(); i++) {
				for (int j = 0; j < raiz.getOrdem(); j++) {
					text.setText(text.getText()+"\t"+raiz.getMatriz()[i][j].getInf());
				}
				text.setText(text.getText()+"\n");
			}
			
			text.setText(text.getText()+"\n");
			
			filhos = ManipulacaoDeTabuleiro.gerarFilhos(raiz);
			for (Tabuleiro tabuleiro : nos) {
				filhos = ManipulacaoDeTabuleiro.removerIgual(tabuleiro, filhos);
			}
			
			raiz = otimoFilhos(filhos,text);
			
			nos.add(raiz);
			aux++;
			
			text.setText(text.getText()+"----------------------------------------------Nível "+aux+"----------------------------------------------");
			text.setText(text.getText()+"\n");
		}
		return nos;
	}
	/**
	 * Acha o melhor uma lista de filhos
	 * @param filhos
	 * @return
	 */
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
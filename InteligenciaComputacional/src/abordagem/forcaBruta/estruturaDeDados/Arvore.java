package abordagem.forcaBruta.estruturaDeDados;

import java.util.ArrayList;

import abordagem.forcaBruta.util.ManipulacaoDeTabuleiro;
import modelo.Tabuleiro;

public class Arvore {
	
		Tabuleiro pai;
		ArrayList<Arvore>  filhos;
		int altura;
		
		public Arvore(Tabuleiro pai, int h){
			this.pai = pai;
			this.altura = h;
			filhos = new ArrayList<>();
		}
		
		public Tabuleiro buscaProfundidade(Tabuleiro avo){
			if(this.altura > 0){
				this.altura--;
				if(pai.analisaOtimo())
					return pai;
				else{
					for(Tabuleiro aux: ManipulacaoDeTabuleiro.removerIgual(avo, ManipulacaoDeTabuleiro.gerarFilhos(pai))){
						Arvore no = new Arvore(aux, this.altura);
						filhos.add(no);
					}
					for(Arvore aux: this.filhos){
						Tabuleiro otimo = aux.buscaProfundidade(pai);
						if(otimo!=null){
							return otimo;
						}
						
					}
					
				}
			}
				return null;
		}

}

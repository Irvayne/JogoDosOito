package abordagem.forcaBruta.estruturaDeDados;

import java.util.Stack;

import abordagem.forcaBruta.util.ManipulacaoDeTabuleiro;
import modelo.Tabuleiro;

public class Arvore {
	
		Tabuleiro pai;
		Stack<Arvore>  filhos;
		int altura;
		
		public Arvore(Tabuleiro pai, int h){
			this.pai = pai;
			this.altura = h;
			filhos = new Stack<>();
		}
		
		public Tabuleiro buscaProfundidade(Tabuleiro avo){
			if(this.altura > 0){
				this.altura--;
				if(pai.analisaOtimo())
					return pai;
				else{
					for(Tabuleiro aux: ManipulacaoDeTabuleiro.removerIgual(avo, ManipulacaoDeTabuleiro.gerarFilhos(pai))){
						Arvore no = new Arvore(aux, this.altura);
						filhos.push(no);
					}
					while(!filhos.isEmpty()){
						Tabuleiro otimo = filhos.pop().buscaProfundidade(pai);
						if(otimo!=null){
							return otimo;
						}
						
					}
					
				}
			}
				return null;
		}

}

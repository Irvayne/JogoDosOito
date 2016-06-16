package abordagem.forcaBruta.estruturaDeDados;

import java.util.ArrayList;
import java.util.List;

import abordagem.forcaBruta.util.ManipulacaoDeTabuleiro;
import modelo.Tabuleiro;

public class Arvore {

	Tabuleiro pai;
	List<Arvore> filhos;
	int altura;

	public Arvore(Tabuleiro pai, int h) {
		this.pai = pai;
		this.altura = h;
		filhos = new ArrayList<>();
	}

	public ArrayList<Tabuleiro> buscaProfundidade(Tabuleiro avo, ArrayList<Tabuleiro> estados) {
		if (this.altura > 0) {
			this.altura--;
			if (pai.analisaOtimo()){
				estados.add(pai);
				return estados;
			}else {
				for (Tabuleiro aux : ManipulacaoDeTabuleiro.removerIgual(avo,
						ManipulacaoDeTabuleiro.gerarFilhos(pai))) {
					Arvore no = new Arvore(aux, this.altura);
					filhos.add(no);
				}
				for(Arvore aux : filhos) {
					ArrayList<Tabuleiro> otimo = aux.buscaProfundidade(pai,estados);
					if (otimo!=null && otimo.get(otimo.size() - 1)!= null){
						estados.add(pai);
						return estados;
					}
						
				}

			}
		}
		return null;
	}
	
	public Tabuleiro buscaEmLargura(Tabuleiro avo){
		if(this.altura > 0){
			this.altura--;
			if(pai.analisaOtimo())
				return pai;
			else{
				for(Tabuleiro aux: ManipulacaoDeTabuleiro.removerIgual(avo,ManipulacaoDeTabuleiro.gerarFilhos(pai))){
					if(aux.analisaOtimo())
						return aux;
					Arvore no = new Arvore(aux, this.altura);
					filhos.add(no);
				}
				
				for(Arvore aux : filhos){
					Tabuleiro otimo = aux.buscaEmLargura(pai);
					if (otimo != null) 
						return otimo;
				}
			}
			
		}
		return null;
	}

}

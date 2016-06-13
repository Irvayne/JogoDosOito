package abordagem.forcaBruta.estruturaDeDados;

import java.util.ArrayList;
import java.util.Stack;

import abordagem.forcaBruta.util.ManipulacaoDeTabuleiro;
import modelo.Tabuleiro;

public class Profundidade {

	

	public static Tabuleiro buscaProfundidade(Tabuleiro raiz, int altura) {
		int nivel = 0;
		Stack<Tabuleiro> nos = new Stack<>();
		if(raiz.analisaOtimo())
			return raiz;
		nos.push(raiz);
		nivel ++;
		
		while(!nos.isEmpty()){
			Tabuleiro pai = nos.pop();
			ArrayList<Tabuleiro> filhos = ManipulacaoDeTabuleiro.removerIgual(pai.getPai(), ManipulacaoDeTabuleiro.gerarFilhos(pai));
			
			nos.addAll(filhos);
			if(nivel == altura){
				nivel--;
				Tabuleiro aux = nos.pop(); 
				if(aux.analisaOtimo())
					return aux;
				
			}
			Tabuleiro aux = nos.pop(); 
			if(aux.analisaOtimo())
				return aux;
			
			
				
			
		}
		
		
		return null;
	}
}

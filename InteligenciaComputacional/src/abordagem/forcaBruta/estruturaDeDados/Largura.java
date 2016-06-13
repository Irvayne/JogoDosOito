package abordagem.forcaBruta.estruturaDeDados;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import abordagem.forcaBruta.util.ManipulacaoDeTabuleiro;
import modelo.Tabuleiro;

public class Largura {
	
	
	public static Tabuleiro buscaEmLargura(Tabuleiro raiz, int altura) throws InterruptedException{
		
		Queue<Tabuleiro> fila = new LinkedList<>();
		fila.add(raiz);
		
		Tabuleiro pai = fila.poll();
		
		ArrayList<Tabuleiro> filhos = ManipulacaoDeTabuleiro.removerIgual(pai.getPai(),
				ManipulacaoDeTabuleiro.gerarFilhos(pai));
		for (Tabuleiro aux : filhos) {
			if(aux.analisaOtimo())
				return aux;
			fila.add(aux);
		}
		
		Tabuleiro paiNew = null;
		for(int nivel = 1; nivel < altura; nivel++){
			int tam = fila.size();
			
			for(int qnt = 0;qnt < tam;qnt++){
				 paiNew = fila.poll();
				ArrayList<Tabuleiro> filhosNew = ManipulacaoDeTabuleiro.removerIgual(paiNew.getPai(),
						ManipulacaoDeTabuleiro.gerarFilhos(paiNew));
				for (Tabuleiro aux : filhosNew) {
					if(aux.analisaOtimo())
						return aux;
					fila.add(aux);
				}
			}
			
		}
		return null;
	}

}

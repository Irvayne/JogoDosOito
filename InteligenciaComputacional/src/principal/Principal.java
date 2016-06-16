//package principal;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Scanner;
//
//import abordagem.buscaHeuristica.Gulosa;
//import abordagem.forcaBruta.estruturaDeDados.Arvore;
//import abordagem.forcaBruta.estruturaDeDados.Largura;
//import modelo.Tabuleiro;
//import modelo.Unidade;
//import visão.Tela;
//
//public class Principal {
//
//	@SuppressWarnings("deprecation")
//	public static void main(String[] args) {
//		new Thread(thread).start();
//		//tela.show();
//		
//		
//		
////		String text="";
////		for(int i = 0; i < ordenado.getOrdem(); i++){
////			for(int j = 0; j < ordenado.getOrdem(); j++){
////				text = text + ordenado.getMatriz()[i][j].getInf()+" ";
////			}
////			text=text +"\n";
////			}
////		tela.textArea.setText(text);
//		
//		
//	}
//	
//	private static Runnable thread = new Runnable() {
//		public void run() {
//			int ordem =3;
//			Unidade matriz[][] = new Unidade[ordem][ordem];
//			Scanner sc1 = new Scanner(System.in); 
//			for (int i = 0; i < ordem; i++) {
//				for (int j = 0; j < ordem; j++) {
//					System.out.println("Valor da unidade da linha "+i+" coluna "+j);
//					int valor = sc1.nextInt();
//					matriz[i][j] = new Unidade(valor);
//				}
//			}
//			Tela tela = new Tela();
//			System.out.println("inciando");
//			
////			matriz[0][0] = new Unidade(-1);
////			matriz[0][1] = new Unidade(1);
////			matriz[0][2] = new Unidade(2);
////			matriz[1][0] = new Unidade(3);
////			matriz[1][1] = new Unidade(4);
////			matriz[1][2] = new Unidade(5);
////			matriz[2][0] = new Unidade(6);
////			matriz[2][1] = new Unidade(7);
////			matriz[2][2] = new Unidade(8);
////			
//			Tabuleiro tabuleiro = new Tabuleiro(3, matriz,null);
//			
//			Arvore arvore = new Arvore(tabuleiro,25);
//			Date dataInicial = new Date();
//			tela.show();
//			
//			//ArrayList<Tabuleiro> estados = arvore.buscaProfundidade(null, new ArrayList<>());
//			ArrayList<Tabuleiro> estados = (ArrayList<Tabuleiro>) Gulosa.buscaGulosa(tabuleiro, 6);
//			
//			
//			String text = "";
//			for(int i = 0; i < estados.size(); i++){
//				Tabuleiro aux = estados.get(i);
//				for(int l = 0; l <aux.getOrdem(); l++){
//					for(int c = 0; c <aux.getOrdem(); c++){
//						if(aux.getMatriz()[l][c].getInf()==-1)
//							text = text + "B ";
//						else
//							text = text + aux.getMatriz()[l][c].getInf()+" ";
//					}
//					text = text +"\n";
//				}
//				
//				tela.textArea.setText(text);
//				try {
//					Thread.sleep(4000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				text = "";
//			}
//			
//			//ArrayList<Tabuleiro> estados = arvore.buscaEmLargura(null, new ArrayList<>());
//			Date dataFinal = new Date();
//			System.out.println(dataFinal.getTime()-dataInicial.getTime());
//
//		}
//	};
//}

package modelo;

public class Tabuleiro {

	private Unidade matriz[][];
	private int ordem;
	private Tabuleiro pai;
	public int altura;
	private int mascara[][];

	public Tabuleiro(int tam) {
		ordem = tam;
		matriz = new Unidade[tam][tam];
		mascara = new int[tam][tam];
		setPai(null);
		

		inicializaMascara();
		inicializaTabuleiro();
	}
	
	public Tabuleiro(int tam, Tabuleiro pai) {
		ordem = tam;
		matriz = new Unidade[tam][tam];
		mascara = new int[tam][tam];
		this.setPai(pai);
		

		inicializaMascara();
		inicializaTabuleiro();
	}

	public Tabuleiro(int tam, Unidade[][] ma, Tabuleiro pai) {
		ordem = tam;
		matriz = new Unidade[tam][tam];
		mascara = new int[tam][tam];
		this.pai = pai;
		for (int l = 0; l < ordem; l++) {
			for (int c = 0; c < ordem; c++) {
				matriz[l][c] = new Unidade(ma[l][c].getInf());
				matriz[l][c].setColuna(ma[l][c].getColuna());
				matriz[l][c].setLinha(ma[l][c].getLinha());
			}
		}
		
		inicializaMascara();
		inicializaTabuleiro();
	}

	public Unidade[][] getMatriz() {
		return matriz;
	}

	public void setMatriz(Unidade[][] matriz) {
		this.matriz = matriz;
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	
	/**
	 * Analisa se o tabuleiro é otimo
	 * 
	 * @param tabuleiro
	 * @return
	 */
	public boolean analisaOtimo() {

		for (int i = 0; i < this.getOrdem(); i++) {
			for (int j = 0; j < this.getOrdem(); j++) {
				if (this.getMatriz()[i][j].getLinha() != i || this.getMatriz()[i][j].getColuna() != j) {
					return false;
				}
			}
		}
		return true;
	}

	public Tabuleiro getPai() {
		return pai;
	}

	public void setPai(Tabuleiro pai) {
		this.pai = pai;
	}
	/**
	 * Inicializa a matriz
	 */
	private void inicializaTabuleiro() {

		for (int i = 0; i < ordem; i++) {
			for (int j = 0; j < ordem; j++) {
				if (matriz[i][j].getInf() != mascara[i][j]) {
					matriz[i][j].setColuna(retornaColuna(matriz[i][j].getInf()));
					matriz[i][j].setLinha(retornaLinha(matriz[i][j].getInf()));
				}else{
					matriz[i][j].setColuna(j);
					matriz[i][j].setLinha(i);
				}
			}
		}
	}
	public int retornaColuna(int numero){
		for (int i = 0; i < ordem; i++) {
			for (int j = 0; j < ordem; j++) {
				if (mascara[i][j] == numero) {
					return j;
				}
			}
		}
		return 0;
	}
	public int retornaLinha(int numero){
		for (int i = 0; i < ordem; i++) {
			for (int j = 0; j < ordem; j++) {
				if (mascara[i][j] == numero) {
					return i;
				}
			}
		}
		return 0;
	}
	
	public void inicializaMascara(){
		int aux = 0;
		for (int i = 0; i < ordem; i++) {
			for (int j = 0; j < ordem; j++) {
				if ((i == ordem-1) && (j == ordem -1) ) {
					mascara[i][j] = -1;
				}else {
					aux++;
					mascara[i][j] = aux;
				}
			}
		}
	}
	public int[][] getMascara() {
		return mascara;
	}
	public void setMascara(int[][] mascara) {
		this.mascara = mascara;
	}

}

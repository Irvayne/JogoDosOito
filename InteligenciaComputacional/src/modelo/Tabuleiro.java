package modelo;

public class Tabuleiro {

	private Unidade matriz[][];
	private int ordem;

	public Tabuleiro(int tam) {
		ordem = tam;
		matriz = new Unidade[tam][tam];
	}

	public Tabuleiro(int tam, Unidade[][] ma) {
		ordem = tam;
		matriz = new Unidade[tam][tam];
		
		for (int l = 0; l < ordem; l++) {
			for (int c = 0; c < ordem; c++) {
				matriz[l][c] = new Unidade(ma[l][c].getInf());
				matriz[l][c].setColuna(ma[l][c].getColuna());
				matriz[l][c].setLinha(ma[l][c].getLinha());
			}
		}
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
	 * Inicializa a matriz
	 */
	private void inicializaTabuleiro() {
		for (int l = 0; l < this.ordem; l++) {

			for (int c = 0; c < this.ordem; c++) {

				if (this.matriz[l][c].getInf() == 1) {
					this.matriz[l][c].setLinha(0);
					this.matriz[l][c].setColuna(0);
				}
				if (this.matriz[l][c].getInf() == 2) {
					this.matriz[l][c].setLinha(0);
					this.matriz[l][c].setColuna(1);
				}
				if (this.matriz[l][c].getInf() == 3) {
					this.matriz[l][c].setLinha(0);
					this.matriz[l][c].setColuna(2);
				}
				if (this.matriz[l][c].getInf() == 4) {
					this.matriz[l][c].setLinha(1);
					this.matriz[l][c].setColuna(0);
				}
				if (this.matriz[l][c].getInf() == 5) {
					this.matriz[l][c].setLinha(1);
					this.matriz[l][c].setColuna(1);
				}
				if (this.matriz[l][c].getInf() == 6) {
					this.matriz[l][c].setLinha(1);
					this.matriz[l][c].setColuna(2);
				}
				if (this.matriz[l][c].getInf() == 7) {
					this.matriz[l][c].setLinha(2);
					this.matriz[l][c].setColuna(0);
				}
				if (this.matriz[l][c].getInf() == 8) {
					this.matriz[l][c].setLinha(2);
					this.matriz[l][c].setColuna(1);
				}
				if (this.matriz[l][c].getInf() == -1) {
					this.matriz[l][c].setLinha(2);
					this.matriz[l][c].setColuna(2);
				}

			}

		}
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

}

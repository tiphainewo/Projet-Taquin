package application;

public class Grille {

	private int taille;
	Piece[][] tableau;
	String image;
	int[] coordTrou;

	public Grille(int size) {
		this.taille = size;
		this.tableau = new Piece[taille][taille];
		image = "lien";
		initTableau();
		coordTrou = new int[] { 3, 3 };
	}

	public int getTaille() {
		return (this.taille);
	}

	/*
	 * initialise la grille en créant toutes les pièces du tableau
	 */
	public void initTableau() {
		int numPiece = 0;
		for (int i = 0; i < this.taille; i++) {
			for (int j = 0; j < this.taille; j++) {
				tableau[j][i] = new Piece(numPiece, j, i);
				numPiece++;
			}
		}
	}

	/*
	 * Affiche la grille de manière textuelle
	 */
	public void afficherGrille() {
		for (int i = 0; i < this.taille; i++) {
			for (int j = 0; j < this.taille; j++) {
				if (j < this.taille - 1) {
					System.out.print(tableau[j][i] + "|");
				} else {
					System.out.println(tableau[j][i]);
				}
			}
		}
	}

	public void checkCoteLibre() {

		// Réinitialise le côté libre pour toutes les pièces
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				tableau[i][j].resetCoteLibre();
			}
		}

		// Case à gauche du vide
		if (coordTrou[0] > 0) {
			tableau[coordTrou[0] - 1][coordTrou[1]].coteLibre = new boolean[] { false, true, false, false };
		}
		// Case à droite du vide
		if (coordTrou[0] < 3) {
			tableau[coordTrou[0] + 1][coordTrou[1]].coteLibre = new boolean[] { false, false, false, true };
		}
		// Case en haut du vide
		if (coordTrou[1] > 0) {
			tableau[coordTrou[0]][coordTrou[1] - 1].coteLibre = new boolean[] { false, false, true, false };
		}
		// Case en bas du vide
		if (coordTrou[1] < 3) {
			tableau[coordTrou[0]][coordTrou[1] + 1].coteLibre = new boolean[] { false, false, false, true };
		}
	}

	public void movePiece(char c) {
		Piece temp=null;
		switch (c) {
		case 'z':
			if (coordTrou[1] < 3) {
				temp = tableau[coordTrou[0]][coordTrou[1]]; // récupère la pièce "vide"
				tableau[coordTrou[0]][coordTrou[1]] = tableau[coordTrou[0]][coordTrou[1] + 1]; // déplace la pièce
				tableau[coordTrou[0]][coordTrou[1] + 1] = temp; // met le trou à la place de la pièce déplacée
				coordTrou[1]+=1;
			}
			break;
		case 'q':
			if (coordTrou[0] < 3) {
				temp = tableau[coordTrou[0]][coordTrou[1]]; // récupère la pièce "vide"
				tableau[coordTrou[0]][coordTrou[1]] = tableau[coordTrou[0] + 1][coordTrou[1]]; // déplace la pièce
				tableau[coordTrou[0] + 1][coordTrou[1]] = temp; // met le trou à la place de la pièce déplacée
				coordTrou[0]+=1;
			}
			break;
		case 's':
			if (coordTrou[1] > 0) {
				temp = tableau[coordTrou[0]][coordTrou[1]]; // récupère la pièce "vide"
				tableau[coordTrou[0]][coordTrou[1]] = tableau[coordTrou[0]][coordTrou[1] - 1]; // déplace la pièce
				tableau[coordTrou[0]][coordTrou[1] - 1] = temp; // met le trou à la place de la pièce déplacée
				coordTrou[1]-=1;
			}
			break;
		case 'd':
			if (coordTrou[0] > 0) {
				temp = tableau[coordTrou[0]][coordTrou[1]]; // récupère la pièce "vide"
				tableau[coordTrou[0]][coordTrou[1]] = tableau[coordTrou[0] - 1][coordTrou[1]]; // déplace la pièce
				tableau[coordTrou[0] - 1][coordTrou[1]] = temp; // met le trou à la place de la pièce déplacée
				coordTrou[0]-=1;
			}
			break;
		}
		afficherGrille();

	}

	public void test() {

	}

}

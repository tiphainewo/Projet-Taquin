package application;

import java.util.Random;

public class Grille {

	private int taille;
	private Piece[][] tableau;
	private int[] coordTrou;

	public Grille(int size) {
		this.taille = size;
		this.tableau = new Piece[size][size];
		this.coordTrou = new int[] {size-1,size-1};
		//image = "lien";
		this.initTableau();
	}


	public int getTaille() {
		return (this.taille);
	}
	
	public int[] getCoordTrou() {
		return this.coordTrou;
	}
	
	public Piece getPiece(int col, int ligne) {
		return this.tableau[col][ligne];
	}

	/*
	 * initialise la grille en créant toutes les pièces du tableau
	 */
	public void initTableau() {
		int numPiece = 1;
		for (int i = 0; i < this.taille; i++) {
			for (int j = 0; j < this.taille; j++) {
				this.tableau[j][i] = new Piece(numPiece, j, i, i == this.taille-1 && j == this.taille-1);
				numPiece++;
			}
		}
		
		
		//randomise la grille
		Random rand = new Random();
		//char[] dirList = new char[] {'z','q','s','d'};
		for (int i = 0; i < 7; i++) {
			//this.movePiece(dirList[rand.nextInt(4)]);
			this.movePiece(rand.nextInt(4));
		}
		
		//Replace la case vide en bas � droite
		while(this.coordTrou[1] < this.taille - 1) this.movePiece('z');
		while(this.coordTrou[0] < this.taille - 1) this.movePiece('q');
	}

	/*
	 * Affiche la grille de manière textuelle
	 */
	public void afficherGrille() {
		for (int i = 0; i < this.taille; i++) {
			for (int j = 0; j < this.taille; j++) {
				if (j < this.taille - 1) {
					System.out.print(this.tableau[j][i] + "|");
				} else {
					System.out.println(this.tableau[j][i]);
				}
			}
		}
		System.out.println("");
	}


	public void movePiece(int i) {
		Piece temp = null;
		switch (i) {
		case 0:
			if (coordTrou[1] < this.taille-1) {
				temp = tableau[coordTrou[0]][coordTrou[1]]; // récupère la pièce "vide"
				tableau[coordTrou[0]][coordTrou[1]] = tableau[coordTrou[0]][coordTrou[1] + 1]; // déplace la pièce
				tableau[coordTrou[0]][coordTrou[1] + 1] = temp; // met le trou à la place de la pièce déplacée
				//Change les coordonnées de la pièce déplacée
				tableau[coordTrou[0]][coordTrou[1]].setCoord(coordTrou);
				coordTrou[1] += 1;
				tableau[coordTrou[0]][coordTrou[1]].setCoord(coordTrou);
			}
			break;
		case 1:
			if (coordTrou[0] < this.taille-1) {
				temp = tableau[coordTrou[0]][coordTrou[1]]; // récupère la pièce "vide"
				tableau[coordTrou[0]][coordTrou[1]] = tableau[coordTrou[0] + 1][coordTrou[1]]; // déplace la pièce
				tableau[coordTrou[0] + 1][coordTrou[1]] = temp; // met le trou à la place de la pièce déplacée
				
				//Change les coordonnées de la pièce déplacée
				tableau[coordTrou[0]][coordTrou[1]].setCoord(coordTrou);
				
				coordTrou[0] += 1;
				tableau[coordTrou[0]][coordTrou[1]].setCoord(coordTrou);
			}
			break;
		case 2:
			if (coordTrou[1] > 0) {
				temp = tableau[coordTrou[0]][coordTrou[1]]; // récupère la pièce "vide"
				tableau[coordTrou[0]][coordTrou[1]] = tableau[coordTrou[0]][coordTrou[1] - 1]; // déplace la pièce
				tableau[coordTrou[0]][coordTrou[1] - 1] = temp; // met le trou à la place de la pièce déplacée
				
				//Change les coordonnées de la pièce déplacée
				tableau[coordTrou[0]][coordTrou[1]].setCoord(coordTrou);
				
				coordTrou[1] -= 1;
				tableau[coordTrou[0]][coordTrou[1]].setCoord(coordTrou);
			}
			break;
		case 3:
			if (coordTrou[0] > 0) {
				temp = tableau[coordTrou[0]][coordTrou[1]]; // récupère la pièce "vide"
				tableau[coordTrou[0]][coordTrou[1]] = tableau[coordTrou[0] - 1][coordTrou[1]]; // déplace la pièce
				tableau[coordTrou[0] - 1][coordTrou[1]] = temp; // met le trou à la place de la pièce déplacée
				
				//Change les coordonnées de la pièce déplacée
				tableau[coordTrou[0]][coordTrou[1]].setCoord(coordTrou);
				
				coordTrou[0] -= 1;
				tableau[coordTrou[0]][coordTrou[1]].setCoord(coordTrou);
			}
			break;
		}
	}
	
	public void movePiece(char c) {
		/** D�place la pi�ce � proximit� du trou selon la valeur de c.
		 * c peut prendre les valeurs `z`,`q`,`s`,`d`
		 * 
		 */
		Piece temp = null;
		switch (c) {
		case 'z':
			if (coordTrou[1] < this.taille-1) {
				temp = tableau[coordTrou[0]][coordTrou[1]]; // récupère la pièce "vide"
				tableau[coordTrou[0]][coordTrou[1]] = tableau[coordTrou[0]][coordTrou[1] + 1]; // déplace la pièce
				tableau[coordTrou[0]][coordTrou[1] + 1] = temp; // met le trou à la place de la pièce déplacée
				//Change les coordonnées de la pièce déplacée
				tableau[coordTrou[0]][coordTrou[1]].setCoord(coordTrou);
				coordTrou[1] += 1;
				tableau[coordTrou[0]][coordTrou[1]].setCoord(coordTrou);
			}
			break;
		case 'q':
			if (coordTrou[0] < this.taille-1) {
				temp = tableau[coordTrou[0]][coordTrou[1]]; // récupère la pièce "vide"
				tableau[coordTrou[0]][coordTrou[1]] = tableau[coordTrou[0] + 1][coordTrou[1]]; // déplace la pièce
				tableau[coordTrou[0] + 1][coordTrou[1]] = temp; // met le trou à la place de la pièce déplacée
				
				//Change les coordonnées de la pièce déplacée
				tableau[coordTrou[0]][coordTrou[1]].setCoord(coordTrou);
				
				coordTrou[0] += 1;
				tableau[coordTrou[0]][coordTrou[1]].setCoord(coordTrou);
			}
			break;
		case 's':
			if (coordTrou[1] > 0) {
				temp = tableau[coordTrou[0]][coordTrou[1]]; // récupère la pièce "vide"
				tableau[coordTrou[0]][coordTrou[1]] = tableau[coordTrou[0]][coordTrou[1] - 1]; // déplace la pièce
				tableau[coordTrou[0]][coordTrou[1] - 1] = temp; // met le trou à la place de la pièce déplacée
				
				//Change les coordonnées de la pièce déplacée
				tableau[coordTrou[0]][coordTrou[1]].setCoord(coordTrou);
				
				coordTrou[1] -= 1;
				tableau[coordTrou[0]][coordTrou[1]].setCoord(coordTrou);
			}
			break;
		case 'd':
			if (coordTrou[0] > 0) {
				temp = tableau[coordTrou[0]][coordTrou[1]]; // récupère la pièce "vide"
				tableau[coordTrou[0]][coordTrou[1]] = tableau[coordTrou[0] - 1][coordTrou[1]]; // déplace la pièce
				tableau[coordTrou[0] - 1][coordTrou[1]] = temp; // met le trou à la place de la pièce déplacée
				
				//Change les coordonnées de la pièce déplacée
				tableau[coordTrou[0]][coordTrou[1]].setCoord(coordTrou);
				
				coordTrou[0] -= 1;
				tableau[coordTrou[0]][coordTrou[1]].setCoord(coordTrou);
			}
			break;
		}
		afficherGrille();

	}

	
	
	public void echangerPieces (int x, int y, int xfinal, int yfinal) {
        /** Echange la pi�ce situ�e aux coordon�es x,y
         * avec celle aux coordonn�es xfinal, yfinal.
         */
        Piece temp = this.tableau[x][y];
        this.tableau[x][y] = this.tableau[xfinal][yfinal];
        this.tableau[xfinal][yfinal] = temp;
        this.tableau[x][y].setCoord(new int[] {x,y});
        this.tableau[xfinal][yfinal].setCoord(new int[] {xfinal,yfinal});
        if (this.coordTrou[1] == y && this.coordTrou[0] == x) this.coordTrou = new int[] {xfinal,yfinal};
        if (this.coordTrou[1] == yfinal && this.coordTrou[0] == xfinal) this.coordTrou = new int[] {x,y};
        
    }
	
	public boolean isWon() {
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				if (!tableau[i][j].estEnPlace()) {
					return false;
				}
			}
		}

		return true;

	}
}

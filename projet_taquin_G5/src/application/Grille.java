package application;

import java.util.Random;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;

public class Grille extends GridPane {

	private int taille;
	Piece[][] tableau;
	String image;
	int[] coordTrou;
	
	GridPane grid;

	public Grille(int size) {
		//pour jeuTexte
		this.taille = size;
		this.tableau = new Piece[taille][taille];
		image = "lien";
		coordTrou = new int[] { 3, 3 };
		//pour java FX
		grid = new GridPane();
		
		initTableau();
	}
	
	public Grille(int size, GridPane gridSB) {
		//pour jeuTexte
		this.taille = size;
		this.tableau = new Piece[taille][taille];
		image = "lien";
		coordTrou = new int[] { 3, 3 };

		//pour java FX
		grid = gridSB;
		
		initTableau();
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
				grid.add(new Piece(numPiece, j, i, 97), j, i);//numerote les piece de la gridpane
				numPiece++;
			}
		}
		
		
		//randomise la grille
		Random rand = new Random();
		for (int i = 0; i < 50; i++) {
			movePiece(rand.nextInt(4));
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


	public void movePiece(int i) {
		Piece temp = null;
		switch (i) {
		case 0:
			if (coordTrou[1] < 3) {
				temp = tableau[coordTrou[0]][coordTrou[1]]; // récupère la pièce "vide"
				tableau[coordTrou[0]][coordTrou[1]] = tableau[coordTrou[0]][coordTrou[1] + 1]; // déplace la pièce
				tableau[coordTrou[0]][coordTrou[1] + 1] = temp; // met le trou à la place de la pièce déplacée
				//Change les coordonnées de la pièce déplacée
				tableau[coordTrou[0]][coordTrou[1]].setCoord(coordTrou);
				coordTrou[1] += 1;
			}
			break;
		case 1:
			if (coordTrou[0] < 3) {
				temp = tableau[coordTrou[0]][coordTrou[1]]; // récupère la pièce "vide"
				tableau[coordTrou[0]][coordTrou[1]] = tableau[coordTrou[0] + 1][coordTrou[1]]; // déplace la pièce
				tableau[coordTrou[0] + 1][coordTrou[1]] = temp; // met le trou à la place de la pièce déplacée
				
				//Change les coordonnées de la pièce déplacée
				tableau[coordTrou[0]][coordTrou[1]].setCoord(coordTrou);
				
				coordTrou[0] += 1;
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
			}
			break;
		}
	}

	
	public void movePiece(char c) {
		Piece temp = null;
		switch (c) {
		case 'z':
			if (coordTrou[1] < 3) {
				temp = tableau[coordTrou[0]][coordTrou[1]]; // récupère la pièce "vide"
				tableau[coordTrou[0]][coordTrou[1]] = tableau[coordTrou[0]][coordTrou[1] + 1]; // déplace la pièce
				tableau[coordTrou[0]][coordTrou[1] + 1] = temp; // met le trou à la place de la pièce déplacée
				//Change les coordonnées de la pièce déplacée
				tableau[coordTrou[0]][coordTrou[1]].setCoord(coordTrou);
				coordTrou[1] += 1;
			}
			break;
		case 'q':
			if (coordTrou[0] < 3) {
				temp = tableau[coordTrou[0]][coordTrou[1]]; // récupère la pièce "vide"
				tableau[coordTrou[0]][coordTrou[1]] = tableau[coordTrou[0] + 1][coordTrou[1]]; // déplace la pièce
				tableau[coordTrou[0] + 1][coordTrou[1]] = temp; // met le trou à la place de la pièce déplacée
				
				//Change les coordonnées de la pièce déplacée
				tableau[coordTrou[0]][coordTrou[1]].setCoord(coordTrou);
				
				coordTrou[0] += 1;
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
			}
			break;
		}
		afficherGrille();
	}
	
	
	public boolean isWon() {

		boolean win = true;

		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				if (!tableau[i][j].estEnPlace()) {
					win = false;
					break;
				}
			}
		}

		return win;

	}
}

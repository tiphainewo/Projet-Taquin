package application;

import javafx.scene.layout.GridPane;

public class Grid extends GridPane  {
	
	int taille;
	
	public Grid(int size) {
		int numPiece = 0;
		
		taille = size;
		
		for (int i = 0; i < this.taille; i++) {
			for (int j = 0; j < this.taille; j++) {
				this.add(new Piece(numPiece, j, i, 97), j, i);
				numPiece++;
			}
		}
	}
}

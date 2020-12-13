package application;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class Grid extends GridPane  {
	
	private int taille;
	
	public Grid(int size) {
		int numPiece = 0;
		this.taille = size;
		
		for (int i = 0; i < this.taille; i++) {
			for (int j = 0; j < this.taille; j++) {
				String imageName="image"+numPiece+".jpg";
				imageName="images/image1.jpg";
				this.getChildren().add(
						new Case(numPiece, j, i,
						97, imageName,
						!(i == this.taille-1 && j == this.taille -1)
						));
				numPiece++;
			}
		}
	}
	
	public Grid(Grille g, String imgPath) {
		this.taille = g.getTaille();
		int caseVideCol = g.getCoordTrou()[0];
		int caseVideRow = g.getCoordTrou()[1];
		int tailleCase = 395/taille;
		for (int i = 0; i < this.taille; i++) {
			for (int j = 0; j < this.taille; j++) {
				Piece piece = g.getPiece(j, i);
				int coordFinalX = piece.getCoordFinal()[1];
				int coordFinalY = piece.getCoordFinal()[0];
				String imageName=imgPath+coordFinalX+coordFinalY+".jpg";
				this.getChildren().add(
						new Case(piece.getId(), j, i,tailleCase, imageName,
						!(i == caseVideCol && j == caseVideRow)
						)
				);
			}	
		}
		
	}
	
	public int getTaille() {
		return taille;
	}
	
	public ArrayList<Case> getCaseChildren() {
		ArrayList<Case> caseList = new ArrayList<>();
		for(Node n : this.getChildren()) {
			if (n instanceof Case) {
				caseList.add((Case) n);
			}
		}
		return caseList;
	}
	
	public void setNumberVisible(boolean visible) {
		for(Case c : this.getCaseChildren()) c.setNumberVisible(visible);
	}
	
	/**
	 * Renvoie la liste (�ventuellement vide) des enfants de la grid
	 * qui se trouvent dans la case � la colonne col et la ligne row.
	 * @param col
	 * @param row
	 * @return
	 */
	public ArrayList<Node> getChildrenAt(int col, int row) {
		ArrayList<Node> list =  new ArrayList<>();
		for(Node node : this.getChildren()) {
			try {
				// this.getChildren() est une ObservableList<Node> de la forme [Groupe, Child, Child, Child.... Child]
				// Le try-catch permet de contourner l'exception renvoy�e par les fonctions
				// GridPane.getRowIndex et GridPane.getColumnIndex lorsqu'elles sont utilis�es sur le premier �l�ment
				// de la liste.
				if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) list.add(node);
			}
			catch(Exception e) {/*System.err.println(e);*/}
		}
		return list;
	}
	
	/**
	 * Echange deux cases
	 * @param colSource
	 * @param rowSource
	 * @param colTarget
	 * @param rowTarget
	 */
	public void swapChildren(int colSource, int rowSource, int colTarget, int rowTarget) {
		ArrayList<Node> source = this.getChildrenAt(colSource,rowSource);
		ArrayList<Node> target = this.getChildrenAt(colTarget,rowTarget);
		for(Node node : source) GridPane.setConstraints(node,colTarget,rowTarget);
		for(Node node : target) GridPane.setConstraints(node,colSource,rowSource);
	}
}

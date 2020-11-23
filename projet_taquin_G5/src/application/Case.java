package application;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Case extends Pane {
	
	private Label num;
	private ImageView img;

	public Case(int number, int x, int y, int sizeImageView, String imgPath, boolean visible) {
		
		//Position de la case dans le gridPane
		GridPane.setColumnIndex(this, x);
		GridPane.setRowIndex(this, y);
		
		//Ajout du Label le chiffre à afficher
		this.num = new Label(Integer.toString(number));
		num.getStyleClass().add("case");
		
		//Ajout de l'ImageView contenant l'image
		img = new ImageView();
		img.setImage(new Image(imgPath));
		img.setFitHeight(sizeImageView);
		img.setFitWidth(sizeImageView);
		
		
		this.getChildren().add(img);
		this.getChildren().add(num);
		this.setVisible(visible);
	}
	
	public void setImage(int size, String imgPath) {
		/** Méthode pas encore testée
		 * 
		 */
		ImageView newImg = new ImageView();
		newImg.setImage(new Image(imgPath));
		newImg.setFitHeight(size);
		newImg.setFitWidth(size);
		
		if (this.img != null) this.getChildren().remove(this.img);
		this.getChildren().add(newImg);
		this.img = newImg;
	}
	
	public void setNumberVisible (boolean visible) {
		num.setVisible(visible);
	}
	
}

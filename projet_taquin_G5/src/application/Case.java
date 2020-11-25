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
		
		//Ajout du Label le chiffre � afficher
		this.num = new Label(Integer.toString(number));
		this.num.setVisible(false);
		
		//Ajout de l'ImageView contenant l'image
		this.img = new ImageView();
		this.img.setImage(new Image(imgPath));
		this.img.setFitHeight(sizeImageView);
		this.img.setFitWidth(sizeImageView);
		
		
		this.getChildren().add(img);
		this.getChildren().add(num);
		this.setVisible(visible);
	}
	
	public void setImage(int size, String imgPath) {
		/** M�thode pas encore test�e
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
		this.num.setVisible(visible);
	}
	
}

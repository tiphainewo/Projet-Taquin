package application;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Case extends Pane {
	
	private Label num;
	private ImageView img;
	
	private int imageSize;
	private String imagePath;

	public Case(int number, int x, int y, int sizeImageView, String imgPath, boolean visible) {
		
		//Position de la case dans le gridPane
		GridPane.setColumnIndex(this, x);
		GridPane.setRowIndex(this, y);
		
		//Ajout du Label le chiffre � afficher
		this.num = new Label(Integer.toString(number));
		num.getStyleClass().add("case");
		
		//Ajout de l'ImageView contenant l'image
		img = new ImageView();
		img.setImage(new Image(imgPath));
		img.setFitHeight(sizeImageView);
		img.setFitWidth(sizeImageView);
		this.imageSize = sizeImageView;
		this.imagePath = imgPath;
		
		
		this.getChildren().add(img);
		this.getChildren().add(num);
		this.setVisible(visible);
	}
	
	public Label getLabel() {
		return this.num;
	}
	
	public ImageView getImageView() {
		return this.img;
	}
	
	public int getImageSize() {
		return this.imageSize;
		
	}
	
	public Case deepCopy(boolean label_visible) {
		Case copy =  new Case(
				Integer.parseInt(this.num.getText()),
				GridPane.getColumnIndex(this), GridPane.getRowIndex(this),
				this.imageSize, this.imagePath, this.isVisible()
				);
		copy.getLabel().setVisible(label_visible);;
		return copy;
	}
	
	/**
	 * Attribue une image a une case
	 * @param size
	 * @param imgPath
	 */
	public void setImage(int size, String imgPath) {
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

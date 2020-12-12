package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CutImage {
	
	
	public CutImage() {
	}

	/**
	 * Decoupe une image donnée en carrés
	 * @param dimension
	 * @param source
	 */
	public void decoupeImage(int dimension, String source) {
		try {

			BufferedImage originalImage = ImageIO.read(new File(source));

			int largeurTotale = originalImage.getWidth();
			int hauteurTotale = originalImage.getHeight();
			
			//Transforme l'image en carré
			if(largeurTotale != hauteurTotale) {
				if (largeurTotale>hauteurTotale) {
					BufferedImage ImageSquare = originalImage.getSubimage(0, 0, hauteurTotale, hauteurTotale);	
					File outputfile = new File("images/ImageSquare.jpg");
					ImageIO.write(ImageSquare, "jpg", outputfile);
					originalImage = ImageSquare;
				}else {
					BufferedImage ImageSquare = originalImage.getSubimage(0, 0, largeurTotale, largeurTotale);
					File outputfile = new File("images/ImageSquare.jpg");
					ImageIO.write(ImageSquare, "jpg", outputfile);
					originalImage = ImageSquare;
				}
			}
			
			int tailleCote = originalImage.getWidth();

			//Determine la taille de chaque pièce
			int  cotePiece= tailleCote / dimension;

			int x = 0;
			int y = 0;

			//Découpe chaque pièce
			for (int i = 0; i < dimension; i++) {
				y = 0;
				for (int j = 0; j < dimension; j++) {
					try {
						BufferedImage ImageCut = originalImage.getSubimage(y, x, cotePiece, cotePiece);
						File outputfile = new File("images/image"+i+j+".jpg");
						ImageIO.write(ImageCut, "jpg", outputfile);
						y += cotePiece;

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				x += cotePiece;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}


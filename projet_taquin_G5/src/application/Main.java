package application;
	
import java.util.Scanner; 
/*
import javafx.application.Application;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
*/

public class Main /*extends Application*/ {
	/*
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			System.out.println("hello");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	public static void main(String[] args) {
		//launch(args);
		jeuTexte();
	}
	public static void jeuTexte() {
		System.out.println("Bienvenue dans la version texte du Taquin. Remettez les pièces dans l'ordre pour gagner.\n");
		Grille grilleTest = new Grille(4);
		grilleTest.afficherGrille();
		Scanner sc = new Scanner(System.in); 
		while(true) {
	        char c = sc.next().charAt(0); 
	        grilleTest.movePiece(c);
	        if (grilleTest.isWon()) {
	        	System.out.println("Vous avez gagné!");
	        }
		}
	}
}

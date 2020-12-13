package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.util.Scanner;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	static Scene scene;
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("TestFXML.fxml"));
			scene = new Scene (root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);	
		//jeuTexte();
	}
	public static void jeuTexte() {
		System.out.println("Bienvenue dans la version texte du Taquin. Remettez les pièces dans l'ordre pour gagner.");
		System.out.println("(z : haut, s : bas, q : gauche, d : droite) \n");
		Grille grilleTest = new Grille(4);
		grilleTest.afficherGrille();
		Scanner sc = new Scanner(System.in); 
		boolean won=false;
		while(!won) {
	        char c = sc.next().charAt(0); 
	        grilleTest.movePiece(c);
	        grilleTest.afficherGrille();
	        if (grilleTest.isWon()) {
	        	System.out.println("Vous avez gagné!");
	        	won=true;
	        }
		}
		sc.close();
	}
}

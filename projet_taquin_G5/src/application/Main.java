package application;
	
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("TestFXML.fxml"));
			Scene scene = new Scene (root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		Grille grilleTest = new Grille(4);
		grilleTest.afficherGrille();
		Scanner sc = new Scanner(System.in); 
		while(true) {
	        char c = sc.next().charAt(0); 
	        grilleTest.movePiece(c);
	        if (grilleTest.isWon()) {
	        	System.out.println("it's won");
	        }
		}
	}
}

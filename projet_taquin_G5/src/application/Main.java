package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import java.util.Scanner; 
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	
	public void start(Stage primaryStage) {
		try {
			Pane mainPane = (Pane) FXMLLoader.load(Main.class.getResource("Test.fxml"));
			BorderPane root = new BorderPane();
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(new Scene(mainPane));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
		Timer chrono = new Timer();
		Grille grilleTest = new Grille(4);
		grilleTest.afficherGrille();
		Scanner sc = new Scanner(System.in); 
		grilleTest.test();
		//chrono.start();
		/*while(true) {
		
        char c = sc.next().charAt(0); 
        grilleTest.movePiece(c);
		}*/
	}
}

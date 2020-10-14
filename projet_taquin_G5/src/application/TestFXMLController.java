package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TestFXMLController implements Initializable {
	
	/*
     * Variables globales correspondant à des objets définis dans la vue (fichier .fxml)
     * Ces variables sont ajoutées à la main et portent le même nom que les fx:id dans Scene Builder
     */
	
	@FXML
    private Label chrono; // value will be injected by the FXMLLoader
    @FXML
    private GridPane grille; // Grille contenant le taquin 
    @FXML
    private Pane fond; // panneau recouvrant toute la fenêtre
    @FXML
    private MenuBar menu; // Menu
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
	
	/*
     * Méthodes appelées lors d'événements dans l'application (fichier .fxml)
     * Ces méthodes sont ajoutées à la main et portent le même nom que les fx:id dans Scene Builder
     */
	
	@FXML
	public void gridKeyPressed (KeyEvent ke) {
		System.out.println("Touche "+ke.getText()+" appuyée via grille.");
	}
	
	@FXML
	public void fondKeyPressed (KeyEvent ke) {
		System.out.println("Touche "+ke.getText()+" appuyée via fond.");
	}
	
	@FXML
	public void anchKeyPressed (KeyEvent ke) {
		System.out.println("Touche "+ke.getText()+" appuyée via ancre.");
	}
	
	@FXML
	public void gridMouseClicked (MouseEvent me) {
		System.out.println("Click "+me.getButton());
		
	}

}

package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class TestFXMLController implements Initializable {
	
	/*
     * Variables globales correspondant � des objets d�finis dans la vue (fichier .fxml)
     * Ces variables sont ajout�es � la main et portent le m�me nom que les fx:id dans Scene Builder
     */
	
	@FXML
    private Label chrono; // value will be injected by the FXMLLoader
    @FXML
    private GridPane grille; // Grille contenant le taquin 
    @FXML
    private Pane fond; // panneau recouvrant toute la fen�tre
    @FXML
    private MenuBar menu; // Menu
    @FXML
    private MenuItem newGame;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
	
	/*
     * M�thodes appel�es lors d'�v�nements dans l'application (fichier .fxml)
     * Ces m�thodes sont ajout�es � la main et portent le m�me nom que les fx:id dans Scene Builder
     */
	
	@FXML
	public void gridKeyPressed (KeyEvent ke) {
		System.out.println("Touche "+ke.getText()+" appuy�e via grille.");
	}
	
	@FXML
	public void fondKeyPressed (KeyEvent ke) {
		System.out.println("Touche "+ke.getText()+" appuy�e via fond.");
	}
	
	@FXML
	public void anchKeyPressed (KeyEvent ke) {
		System.out.println("Touche "+ke.getText()+" appuy�e via ancre.");
	}
	
	@FXML
	public void gridMouseClicked (MouseEvent me) {
		System.out.println("Click "+me.getButton());
		
	}
	@FXML
    void launchTimer(ActionEvent event) {
    	Timer timer = new Timer(60);
    }
    
    class Timer extends Pane{
		private Timeline animation;
		public int temps;
		private String s = "";
		private Timer(int t) {
			temps = t;
			animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> timepass()));
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.play();
		}
		
		private void timepass() {
			if(temps>0) {
				temps--;
			}
			s = temps+"";
			chrono.setText(s);
		}
	}

}

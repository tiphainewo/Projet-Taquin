package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


public class TestFXMLController implements Initializable {
	
	/*
     * Variables globales correspondant � des objets d�finis dans la vue (fichier .fxml)
     * Ces variables sont ajout�es � la main et portent le m�me nom que les fx:id dans Scene Builder
     */
	
	// value will be injected by the FXMLLoader
	@FXML
    private Pane fond; // panneau recouvrant toute la fen�tre
    @FXML
    private GridPane grille_init; // Grille initiale 
    @FXML
    private BorderPane borderPane;
    @FXML
    private MenuBar menu; // Menu
    @FXML
    private MenuItem newGame;
    @FXML
    private MenuItem buttonChiffres;
    @FXML
    private MenuItem darktheme;
    @FXML
    private MenuItem lightTheme;
    @FXML
    private MenuItem sepiaTheme;
    @FXML
    private Pane chronoPane;
    @FXML
    private Label chrono;
    @FXML
    private Pane scorePane;
    @FXML
    private Label score;
    
    
    
    private Grille taquin;
    private Grid grid;
    
    private int nbMove;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		grid = new Grid(4);
		grid.setOnMouseClicked(grille_init.getOnMouseClicked());
		grille_init = null;
	}
	
	
	/*
     * M�thodes appel�es lors d'�v�nements dans l'application (fichier .fxml)
     * Ces m�thodes sont ajout�es � la main et portent le m�me nom que les fx:id dans Scene Builder
     */
	
	/*@FXML
    void launchTimer(ActionEvent event) {
    	
    }*/
	
	@FXML
	void setNewGame(ActionEvent event) { // Action du bouton "New Game" dans le menu

		taquin = new Grille(4);
		
		taquin.afficherGrille(); //debug
		
		Grid g = new Grid(taquin, "File:images/image");
		g.setOnMouseClicked(grid.getOnMouseClicked());
		grid = g;
		
		grid.setGridLinesVisible(false);
		grid.setNumberVisible(buttonChiffres.getText().equals("Cacher les chiffres"));
		borderPane.setCenter(grid);
		
		nbMove = 0;
		score.setText("0");
		
		// Lancement du chrono
		Timer timer = new Timer(6);
	}
    
	@FXML
    void afficherFin() {
    	System.out.println("fin");
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
			else {
				afficherFin();
				animation.stop();
			}
			s = temps+"";
			chrono.setText(s);
		}
	}
    
    @FXML
    void toggleChiffres(ActionEvent e) {
    	String current = buttonChiffres.getText();
    	if(current.equals("Afficher les chiffres")) {
    		grid.setNumberVisible(true);
    		buttonChiffres.setText("Cacher les chiffres");
    	}
    	if(current.equals("Cacher les chiffres")) {
    		grid.setNumberVisible(false);
    		buttonChiffres.setText("Afficher les chiffres");
    	}
    }
    
    @FXML
    void putDarkTheme(ActionEvent event) {
    	Main.scene.getStylesheets().clear();
    	Main.scene.getStylesheets().add("application/darkTheme.css");
    }
    
    @FXML
    void putLightTheme(ActionEvent event) {
    	Main.scene.getStylesheets().clear();
    	Main.scene.getStylesheets().add("application/application.css");
    }
    
    @FXML
    void putSepiaTheme(ActionEvent event) {
    	Main.scene.getStylesheets().clear();
    	Main.scene.getStylesheets().add("application/sepiaTheme.css");
    }

	public void gridMouseClicked (MouseEvent me) {
		int casex = (int) (me.getX()*grid.getColumnCount()/grid.getWidth());
		int casey = (int) (me.getY()*grid.getRowCount()/grid.getHeight());
		int caseVideCol = taquin.getCoordTrou()[0];
		int caseVideRow = taquin.getCoordTrou()[1];
		if((caseVideCol == casex && (caseVideRow == casey-1 || caseVideRow == casey+1)) 
				||(caseVideRow == casey && (caseVideCol == casex-1 || caseVideCol == casex+1))) {
			// On teste si la case vide et adjacente � la case cliqu�e
			grid.swapChildren(casex, casey, caseVideCol,caseVideRow);
			taquin.echangerPieces(casex, casey, caseVideCol, caseVideRow);
			
			score.setText(Integer.toString(++nbMove));
		}
		taquin.afficherGrille(); //debug
	}
}

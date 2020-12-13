package application;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.scene.control.Dialog;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;


public class TestFXMLController implements Initializable {
	
	/*
     * Variables globales correspondant � des objets d�finis dans la vue (fichier .fxml)
     * Ces variables sont ajout�es � la main et portent le m�me nom que les fx:id dans Scene Builder
     */
	
	@FXML
    private Label chrono; // value will be injected by the FXMLLoader
    @FXML
    private GridPane grille_init; // Grille initiale 
    @FXML
    private Pane fond; // panneau recouvrant toute la fen�tre
    @FXML
    private MenuBar menu; // Menu
    @FXML
    private MenuItem quitButton;
    @FXML
    private MenuItem newGame;
    @FXML
    private MenuItem darktheme;
    @FXML
    private MenuItem lightTheme;
    @FXML
    private MenuItem sepiaTheme;
    @FXML
    private Pane chronoPane;
    @FXML
    private Button nouvellePartie;
    @FXML
    private AnchorPane dialog;
    @FXML
    private TextField numTaille;
    
    private BorderPane borderPane;
    @FXML
    private Label victory;
    
    private Grille taquin;
    private Grid grid;
    private String pathImage="images/image.jpg";
    private Timer timer;
    public Timeline animation;
    
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	
	
	/*
     * M�thodes appel�es lors d'�v�nements dans l'application (fichier .fxml)
     * Ces m�thodes sont ajout�es � la main et portent le m�me nom que les fx:id dans Scene Builder
     */
	
	/*@FXML
    void launchTimer(ActionEvent event) {
    	
    }*/
	
	/**
	 * Ferme la fenetre 
	 * @param event : clic sur le bouton quitter
	 */
	@FXML
    void quitGame(ActionEvent event) {
		System.exit(0);
    }
	
	/**
	 * Lance la fenetre de nouvelle partie ou on choisit la taille et l'image
	 * @param event : Action du bouton "New Game" dans le menu
	 */
	@FXML
	void newGame(ActionEvent event) {
		dialog.setVisible(false);
		int tailleGrille=4;
		if(numTaille.getText()!=null) {
			try {
				tailleGrille= Integer.parseInt(numTaille.getText());
			} catch (NumberFormatException nfe){
				
			}
		}
		
		//Découpe l'image à la bonne taille selon le nombre de cases
		CutImage cutImage = new CutImage();
		cutImage.decoupeImage(tailleGrille,pathImage);

		taquin = new Grille(tailleGrille);
		
		taquin.afficherGrille(); //debug
		
		Grid g = new Grid(taquin, "File:images/image");
		if(grid == null) { // Cas o� il s'agit de la premi�re partie apr�s le lancement de l'application
			grid = g;
			grid.setOnMouseClicked(grille_init.getOnMouseClicked());
			grille_init = null; // Hello garbage collector
		}
		else {
			g.setOnMouseClicked(grid.getOnMouseClicked());
			grid = g;
		}
		grid.setGridLinesVisible(true);
		borderPane.setCenter(grid);
		
		// Lancement du chrono
		if(animation!=null) {
			animation.stop();
		}
		timer = new Timer(0);
		victory.setText("");
	}
    
	public void afficherFin(){
		String temps=chrono.getText();
		String phrase="Vous avez fini le puzzle en "+temps+" secondes!";
		chrono.setText(phrase);
	}
	
	/**
	 * Crée une classe Timer a partir d'une animation
	 *
	 */
    class Timer extends Pane{
		
		public int temps;
		private String s = "";
		private Timer(int t) {
			temps = t;
			animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> timepass()));
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.play();
		}
		
		private void timepass() {
			temps++;
			s = temps+"";
			chrono.setText(s);
		}
	}
    
    /**
     * Les trois prochaines méthodes changent l'aspect de la fenêtre
     * @param event : clic sur le bouton correspondant au theme
     */
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
    
    /**
     * Ouvre la fenetre de dialogue quand on lance une nouvelle partie
     * @param event
     */
    @FXML
    void openDialog(ActionEvent event) {
    	dialog.setVisible(true);
    }
    
    /**
     * Ouvre l'explorateur de fichier pour choisir une nouvelle image
     * @param event : clic sur le bouton choisir une image
     */
    @FXML
    void openFileChooser(ActionEvent event) {
    	final FileChooser fileChooser = new FileChooser();
    	fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
    	File selectedFile = fileChooser.showOpenDialog(fond.getScene().getWindow());
    	if (selectedFile != null) {
    		pathImage=selectedFile.getAbsolutePath();
    	}
 
        
    }

	/**
     * Deplace les cases quand on clique dessus
     * @param me
     */
    public void gridMouseClicked (MouseEvent me) {
    	if(!taquin.isWon()) {
	        int casex = (int) (me.getX()*grid.getColumnCount()/grid.getWidth());
	        int casey = (int) (me.getY()*grid.getRowCount()/grid.getHeight());
	        int caseVideCol = taquin.getCoordTrou()[0];
	        int caseVideRow = taquin.getCoordTrou()[1];
	        if((caseVideCol == casex && (caseVideRow == casey-1 || caseVideRow == casey+1)) 
	                ||(caseVideRow == casey && (caseVideCol == casex-1 || caseVideCol == casex+1))) {
	            // On teste si la case vide et adjacente � la case cliqu�e
	            grid.swapChildren(casex, casey, caseVideCol,caseVideRow);
	            taquin.echangerPieces(casex, casey, caseVideCol, caseVideRow);
	        }
	        if(taquin.isWon()) {
	        	System.out.println("win");
	        	grid.getChildren().add(new Case(15, grid.getTaille() - 1, grid.getTaille() - 1, 97,"File:images/image33.jpg", true));
	        	for(Case x : grid.getCaseChildren()) {
	        		x.setNumberVisible(false);
	        	}
	        	victory.setText("Victoire!");
	        }
    	}
    	
        taquin.afficherGrille(); //debug
    }
}

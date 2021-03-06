package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private Label chronoLabel;
    @FXML
    private Button nouvellePartie;
    @FXML
    private AnchorPane dialog;
    @FXML
    private TextField numTaille;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Label victory;
    @FXML
    private Pane scorePane;
    @FXML
    private Label score;
    @FXML
    private MenuItem buttonChiffres;
    
    private Grille taquin;
    private Grid grid;
    private String pathImage="images/image.jpg";
    public Timeline animation;
    public TranslateTransition tt;
    private Case lastMovedCase;
    
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		tt = new TranslateTransition();
	}
	
	
	/*
     * M�thodes appel�es lors d'�v�nements dans l'application (fichier .fxml)
     * Ces m�thodes sont ajout�es � la main et portent le m�me nom que les fx:id dans Scene Builder
     */

	
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
				if (tailleGrille<2 || tailleGrille>8) {
					tailleGrille=4;
				}
			} catch (NumberFormatException nfe){
				
			}
		}
		
		//Découpe l'image à la bonne taille selon le nombre de cases
		CutImage cutImage = new CutImage();
		cutImage.decoupeImage(tailleGrille,pathImage);

		taquin = new Grille(tailleGrille);
		
		
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
		grid.setGridLinesVisible(false);
		grid.setNumberVisible(buttonChiffres.getText().equals("Cacher les chiffres"));
		borderPane.setCenter(grid);
		
		// Lancement du chrono
		if(animation!=null) {
			animation.stop();
		}
		@SuppressWarnings("unused")
		Timer timer = new Timer(0);
		victory.setText("");
		chronoLabel.setText("Temps écoulé");
		score.setText("0");
	}
    
	public void afficherFin(){
		victory.setText("Victoire!");
    	if(animation!=null) {
			animation.stop();
		}
		chronoLabel.setText("");
		String temps=chrono.getText();
		String phrase="Vous avez fini \nle puzzle en "+temps+"\n secondes!";
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
	        
	        String direction = "";
	        if(caseVideCol == casex && caseVideRow == casey-1) direction = "up";
	        if(caseVideCol == casex && caseVideRow == casey+1) direction = "down";
	        if(caseVideRow == casey && caseVideCol == casex-1) direction = "left";
	        if(caseVideRow == casey && caseVideCol == casex+1) direction = "right";
	        
	        if(direction != "") {
	        	
	        	Case movingCase = grid.getCaseChildrenAt(casex, casey).get(0);
	        	Case movingCase_copy = movingCase.deepCopy(buttonChiffres.getText().equals("Cacher les chiffres"));
	            
	        	grid.swapChildren(casex, casey, caseVideCol, caseVideRow);
	            taquin.echangerPieces(casex, casey, caseVideCol, caseVideRow);
	            
	            grid.getChildren().add(movingCase_copy);

	            if(!tt.getStatus().equals(Status.STOPPED)) { //Cas o� une autre animation est encore en cours
	            	try {
	            		tt.getNode().setVisible(false);
	            		grid.getChildren().remove(tt.getNode());
	            		lastMovedCase.setVisible(true);
	            	} catch(NullPointerException npe) {}
	            }
	            
	            lastMovedCase = movingCase;
	            lastMovedCase.setVisible(false);
	            
            	tt = new TranslateTransition(Duration.millis(250), movingCase_copy);

            	tt.setOnFinished(
            			new EventHandler<ActionEvent>() {
            				public void handle(ActionEvent a) {
             					tt.getNode().setVisible(false);
            					grid.getChildren().remove(tt.getNode());
            					lastMovedCase.setVisible(true);
            				}
            			}
            			);
	            
	            switch(direction) {
		            case "right":
		            	tt.setByX(movingCase_copy.getImageSize());
		            	tt.play();
		            	break;
		            case "left":
		            	tt.setByX(-movingCase_copy.getImageSize());
		            	tt.play();
		            	break;
		            case "up":
		            	tt.setByY(-movingCase_copy.getImageSize());
		            	tt.play();
		            	break;
		            case "down":
		            	tt.setByY(movingCase_copy.getImageSize());
		            	tt.play();
		            	break;
		            default:
	            }
	            
	            
	            score.setText(Integer.toString(1+Integer.parseInt(score.getText())));
	        }
	        if(taquin.isWon()) {
	        	//System.out.println("win");
	        	
	        	for(Case x : grid.getCaseChildren()) {
	        		x.setNumberVisible(false);
	        		//x.setVisible(true);
	        	}
	        	for(Case x : grid.getCaseChildrenAt(casex, casey)) {
	        		x.setVisible(true);
	        	}
	        	
	        	afficherFin();
	        }
    	}
    }
}

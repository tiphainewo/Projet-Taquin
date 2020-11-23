package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
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
    private GridPane grille_init; // Grille initiale 
    @FXML
    private Pane fond; // panneau recouvrant toute la fenêtre
    @FXML
    private MenuBar menu; // Menu
    @FXML
    private BorderPane borderPane;
    
    
    private Grille taquin;
    private Grid grid;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		taquin = new Grille(4);
		
		taquin.afficherGrille(); //debug
		
		grid = new Grid(taquin);
		grid.setGridLinesVisible(true);
		grid.setOnMouseClicked(grille_init.getOnMouseClicked());
		grille_init = null; // Hello garbage collector
		borderPane.setCenter(grid);
	}
	/*
     * Méthodes appelées lors d'événements dans l'application (fichier .fxml)
     * Ces méthodes sont ajoutées à la main et portent le même nom que les fx:id dans Scene Builder
     */
	
	@FXML
	public void gridMouseClicked (MouseEvent me) {
		int casex = (int) (me.getX()*grid.getColumnCount()/grid.getWidth());
		int casey = (int) (me.getY()*grid.getRowCount()/grid.getHeight());
		int caseVideCol = taquin.getCoordTrou()[0];
		int caseVideRow = taquin.getCoordTrou()[1];
		if((caseVideCol == casex && (caseVideRow == casey-1 || caseVideRow == casey+1)) 
				||(caseVideRow == casey && (caseVideCol == casex-1 || caseVideCol == casex+1))) {
			// On teste si la case vide et adjacente à la case cliquée
			grid.swapChildren(casex, casey, caseVideCol,caseVideRow);
			taquin.echangerPieces(casey, casex, caseVideRow, caseVideCol);
		}
		taquin.afficherGrille(); //debug
	}
	
	public Grid get_grid() {
		return grid;
	}
}

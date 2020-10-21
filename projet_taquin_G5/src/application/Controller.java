package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Label timerDisplay;

    @FXML
    private Button start;

    @FXML
    void onStartClick(ActionEvent event) {
    	timerDisplay.setText("hello");
    }

}
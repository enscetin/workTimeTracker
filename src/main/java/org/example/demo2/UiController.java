package org.example.demo2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UiController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
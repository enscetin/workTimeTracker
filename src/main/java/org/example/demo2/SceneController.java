/*
package org.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class SceneController {
    private Scene scene;
    private Stage stage;
    private Parent root;

    public void switchToMainPage (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/fxmlPart/MainPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToStopwatch (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/fxmlPart/Stopwatch.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToResults (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/fxmlPart/Results.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
*/

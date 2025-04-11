package org.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainPageController {
    @FXML
    Label dateOnMainScreen;

    private Scene scene;
    private Stage stage;
    private Parent root;

    @FXML
    private Button stopButton;

    public void switchToMainPage (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/fxmlPart/MainPage.fxml"));
        Parent root = loader.load(); // Load edilen Parent'ı al

        // Controller'ı al
        MainPageController controller = loader.getController();

        // Label'ı güncelle
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        controller.dateOnMainScreen.setText(currentDate.format(formatter));

        // Sahneyi değiştir
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    public void switchToStopwatch (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/fxmlPart/Stopwatch.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stopButton.setDisable(true);
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

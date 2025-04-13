package controllers;

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

public class ResultsController {
    @FXML
    Label dateOnMainScreen;

    private Scene scene;
    private Stage stage;
    private Parent root;

    @FXML
    private Button startButton;

    public void switchToMainPage (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/fxmlPart/MainPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        /*LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        dateOnMainScreen.setText(currentDate.format(formatter));*/
        stage.setScene(scene);
        stage.show();
    }

    public void switchToStopwatch (ActionEvent event) throws IOException {
        /*Parent root = FXMLLoader.load(getClass().getResource("/org/example/fxmlPart/Stopwatch.fxml"));*/
        // Stopwatch.fxml'i yükleyin

        // Yeni bir FXMLLoader oluşturun ve root ile ilişkilendirin
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/fxmlPart/Stopwatch.fxml"));
        root = loader.load();

        // StopwatchController'a erişin
        Stopwatch stopwatchController = loader.getController();

        // stopButton'ı devre dışı bırakın
        stopwatchController.makeStopButtonInvisible();

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

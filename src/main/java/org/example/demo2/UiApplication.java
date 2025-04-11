package org.example.demo2;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class UiApplication extends Application {
    @FXML
    Label dateOnMainScreen;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UiApplication.class.getResource("/org/example/fxmlPart/MainPage.fxml"));
        /*LocalDate currentDate = LocalDate.now();
        dateOnMainScreen.setText(currentDate.toString());*/
        Scene scene = new Scene(fxmlLoader.load());
        MainPageController controller = fxmlLoader.getController();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        controller.dateOnMainScreen.setText(currentDate.format(formatter));
        stage.setTitle("Work Time Tracker");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
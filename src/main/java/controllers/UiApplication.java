package controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UiApplication extends Application {
    @FXML
    Label dateOnMainScreen;

    @Override
    public void start(Stage stage) {
        // Ekran boyutunu al
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Ekran boyutuna göre sahne oluştur
        Scene scene = new Scene(new StackPane(), screenBounds.getWidth(), screenBounds.getHeight());
        util.ViewNavigator.setScene(scene);

        // Sahneyi ekrana yerleştir
        stage.setScene(scene);
        stage.setTitle("Work Time Tracker");
        stage.setX(screenBounds.getMinX());
        stage.setY(screenBounds.getMinY());
        stage.setWidth(screenBounds.getWidth());
        stage.setHeight(screenBounds.getHeight());
        stage.setResizable(true);
        stage.show();

        // Ana ekranı güncel tarih ile yükle
        util.ViewNavigator.switchToWithController("/org/example/fxmlPart/MainPage.fxml", (MainPageController controller) -> {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
            controller.dateOnMainScreen.setText(currentDate.format(formatter));
        });

        util.ViewNavigator.switchToWithController("/org/example/fxmlPart/Stopwatch.fxml", Stopwatch::makeStopButtonInvisible);
    }

    public static void main(String[] args) {
        launch();
    }
}
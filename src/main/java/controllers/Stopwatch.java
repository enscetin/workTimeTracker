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
import model.WorkSession;
import service.WorkSessionService;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class Stopwatch {
    @FXML
    Label dateOnMainScreen;

    private Scene scene;
    private Stage stage;
    private Parent root;
    private WorkSessionService workSessionService = new WorkSessionService();
    private Timer timer;
    private WorkSession workSession;

    @FXML
    public static Label timerLabel;

    @FXML
    public Button startButton, stopButton;

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

 /*   @FXML
    public void initialize() {
        stopButton.setDisable(true); // Başlangıçta durdurma butonu kapalı olsun

    }*/

    @FXML
    public void startSession() {
        workSessionService.startSession();
        /*startTime = LocalDateTime.now();
        startTimer();*/
        startButton.setDisable(true);
        startButton.setVisible(false);
        stopButton.setDisable(false);
        stopButton.setVisible(true);
    }

    @FXML
    public void makeStopButtonInvisible(){
        stopButton.setDisable(true);
        stopButton.setVisible(false);
    }

    @FXML
    public void stopSession() {
        workSessionService.stopSession();
        /*timerLabel.setText("You have reached" + workSessionService.getCurrentSession().getDuration() + "minutes");*/
       /* stopTimer();*/
        startButton.setDisable(false);
        startButton.setVisible(true);
        stopButton.setDisable(true);
        stopButton.setVisible(false);
    }

    /*private void startTimer() {
        timer = new Timer(true); // Daemon thread olarak başlatıyoruz
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                LocalDateTime now = LocalDateTime.now();
                Duration duration = Duration.between(startTime, now);
                long minutes = duration.toMinutes();
                long seconds = duration.getSeconds() % 60;
                long hours = duration.toHours();

                // JavaFX thread'inde UI güncellemesi
                javafx.application.Platform.runLater(() ->
                        timerLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds))
                );
            }
        }, 0, 1000);
    }*/

   /* private void startTimer() {
        WorkSession currentWorkSession = new WorkSession();
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }*/


}


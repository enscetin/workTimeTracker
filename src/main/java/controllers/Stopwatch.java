package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.WorkSession;
import service.WorkSessionService;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class Stopwatch {
    private final WorkSessionService workSessionService = new WorkSessionService();
    private Timer timer;

    @FXML
    private Label timerLabel;

    @FXML
    private Button startButton, stopButton;

    @FXML
    Label dateOnMainScreen;

    @FXML
    public void startSession() {
        workSessionService.startSession();
        startButton.setDisable(true);
        startButton.setVisible(false);
        stopButton.setDisable(false);
        stopButton.setVisible(true);
        startTimer();
    }

    @FXML
    public void stopSession() {
        workSessionService.stopSession();
        stopTimer();
        timerLabel.setText("00:00:00");
        startButton.setDisable(false);
        startButton.setVisible(true);
        stopButton.setDisable(true);
        stopButton.setVisible(false);
    }

    private void startTimer() {
        stopTimer();
        timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                WorkSession session = workSessionService.getCurrentSession();
                if (session != null && session.getStartTime() != null) {
                    Duration elapsed = Duration.between(session.getStartTime(), LocalDateTime.now());
                    long hours = elapsed.toHours();
                    long minutes = elapsed.toMinutesPart();
                    long seconds = elapsed.toSecondsPart();

                    Platform.runLater(() -> timerLabel.setText(
                            String.format("%02d:%02d:%02d", hours, minutes, seconds)));
                }
            }
        }, 0, 1000);
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @FXML
    public void makeStopButtonInvisible() {
        stopButton.setDisable(true);
        stopButton.setVisible(false);
    }


    @FXML
    public void switchToMainPage() {
        util.ViewNavigator.switchToWithController("/org/example/fxmlPart/MainPage.fxml", (MainPageController controller) -> {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
            controller.dateOnMainScreen.setText(currentDate.format(formatter));
        });
    }


    @FXML
    public void switchToStopwatch() {
        util.ViewNavigator.switchTo("/org/example/fxmlPart/Stopwatch.fxml");
    }

    @FXML
    public void switchToResults() {
        util.ViewNavigator.switchTo("/org/example/fxmlPart/Results.fxml");
    }
}

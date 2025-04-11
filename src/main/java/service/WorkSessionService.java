package service;

import javafx.fxml.FXMLLoader;
import model.WorkSession;
import org.example.demo2.Stopwatch;
import repository.WorkSessionRepository;

import java.time.LocalDateTime;
import java.util.List;

public class WorkSessionService {
    public WorkSessionRepository repository;
    public WorkSession currentSession = new WorkSession(0);

    public WorkSessionService() {
        this.repository = new WorkSessionRepository();
    }

    public WorkSession getCurrentSession() {
        return currentSession;
    }

    public void startSession() {
        /*if (currentSession == null) {
            currentSession = new WorkSession(0);
        }*/
        currentSession.setStartTime(LocalDateTime.now());
    }

    public void stopSession() {
        if (currentSession.getStartTime() != null) {
            currentSession.setEndTime(LocalDateTime.now());
            repository.saveSession(currentSession);
            /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/fxmlPart/Stopwatch.fxml"));
            Stopwatch stopwatchController = loader.getController();*/
            currentSession.setStartTime(null);
        }
    }

    public void addManualSession(int minutes) {
        WorkSession session = new WorkSession(minutes);
        repository.saveSession(session);
    }

    public List<WorkSession> getSessionHistory() {
        return repository.getAllSessions();
    }
}

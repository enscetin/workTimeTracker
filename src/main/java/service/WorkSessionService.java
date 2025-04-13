package service;

import model.WorkSession;
import repository.WorkSessionRepository;

import java.time.LocalDateTime;
import java.util.List;

public class WorkSessionService {
    private final WorkSessionRepository repository = new WorkSessionRepository();
    private WorkSession currentSession;

    public WorkSessionService() {
        repository.createTableIfNotExists();
    }

    public void startSession() {
        currentSession = new WorkSession();
    }

    public void stopSession() {
        if (currentSession != null && currentSession.getStartTime() != null) {
            currentSession.setEndTime(LocalDateTime.now());
            repository.saveOrUpdateTodaySession(currentSession);
            currentSession = null;
        }
    }

    public WorkSession getCurrentSession() {
        return currentSession;
    }

    public List<WorkSession> getSessionHistory() {
        return repository.getAllSessions();
    }
}

package repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.WorkSession;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WorkSessionRepository {
    /*private static final String FILE_PATH = "repository/work_sessions.json";*/
    public static final String FILE_PATH = "repository/work_sessions.json";

    private static final Gson gson = new Gson();

    public void saveSession(WorkSession session) {
        List<WorkSession> sessions = getAllSessions();
        sessions.add(session);
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(sessions, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<WorkSession> getAllSessions() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return new ArrayList<>();

        try (Reader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<WorkSession>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<WorkSession> getSessionsByDate(LocalDate date) {
        return getAllSessions().stream()
                .filter(session -> session.getStartTime().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }

}

package repository;

import model.WorkSession;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WorkSessionRepository {
    private static final String DB_URL = "jdbc:sqlite:work_sessions.db";

    public WorkSessionRepository() {
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS work_session (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "start_time TEXT NOT NULL," +
                "end_time TEXT NOT NULL," +
                "duration_minutes INTEGER)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveSession(WorkSession session) {
        String sql = "INSERT INTO work_session(start_time, end_time, duration_minutes) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, session.getStartTime().toString());
            pstmt.setString(2, session.getEndTime().toString());
            pstmt.setInt(3, session.getDuration());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<WorkSession> getAllSessions() {
        List<WorkSession> sessions = new ArrayList<>();
        String sql = "SELECT * FROM work_session";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                LocalDateTime start = LocalDateTime.parse(rs.getString("start_time"));
                LocalDateTime end = LocalDateTime.parse(rs.getString("end_time"));
                long duration = rs.getLong("duration_minutes");
                sessions.add(new WorkSession(start, end, (int) duration));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sessions;
    }

    public List<WorkSession> getSessionsByDate(LocalDate date) {
        List<WorkSession> all = getAllSessions();
        List<WorkSession> filtered = new ArrayList<>();
        for (WorkSession session : all) {
            if (session.getStartTime().toLocalDate().equals(date)) {
                filtered.add(session);
            }
        }
        return filtered;
    }
}
package repository;

import model.WorkSession;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static database.DatabaseManager.getConnection;

public class WorkSessionRepository {

    public void createTableIfNotExists() {
        String sql = """
            CREATE TABLE IF NOT EXISTS work_session (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                start_time TEXT NOT NULL,
                end_time TEXT NOT NULL,
                duration_minutes INTEGER
            );
        """;

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveOrUpdateTodaySession(WorkSession session) {
        WorkSession today = findTodaySession();

        if (today == null) {
            save(session);
        } else {
            updateTodaySession(session);
        }
    }

    private void save(WorkSession session) {
        String sql = "INSERT INTO work_session(start_time, end_time, duration_minutes) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, session.getStartTime().toString());
            pstmt.setString(2, session.getEndTime().toString());
            pstmt.setInt(3, session.getDurationMinutes());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateTodaySession(WorkSession session) {
        String sql = """
            UPDATE work_session 
            SET end_time = ?, duration_minutes = ?
            WHERE DATE(start_time) = DATE('now');
        """;

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, session.getEndTime().toString());
            pstmt.setInt(2, session.getDurationMinutes());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public WorkSession findTodaySession() {
        String sql = "SELECT * FROM work_session WHERE DATE(start_time) = DATE('now')";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return new WorkSession(
                        LocalDateTime.parse(rs.getString("start_time")),
                        LocalDateTime.parse(rs.getString("end_time")),
                        rs.getInt("duration_minutes")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<WorkSession> getAllSessions() {
        List<WorkSession> sessions = new ArrayList<>();
        String sql = "SELECT * FROM work_session";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                sessions.add(new WorkSession(
                        LocalDateTime.parse(rs.getString("start_time")),
                        LocalDateTime.parse(rs.getString("end_time")),
                        rs.getInt("duration_minutes")
                ));
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

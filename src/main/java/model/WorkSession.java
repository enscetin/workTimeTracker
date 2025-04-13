package model;

import java.time.Duration;
import java.time.LocalDateTime;

public class WorkSession {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int durationMinutes;

    public WorkSession() {
        this.startTime = LocalDateTime.now();
        this.endTime = null;
        this.durationMinutes = 0;
    }

    public WorkSession(LocalDateTime start, LocalDateTime end, int durationMinutes) {
        this.startTime = start;
        this.endTime = end;
        this.durationMinutes = durationMinutes;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
        this.durationMinutes = (int) Duration.between(startTime, endTime).toMinutes();
    }

    public int getDurationMinutes() {
        if (endTime == null) {
            return (int) Duration.between(startTime, LocalDateTime.now()).toMinutes();
        }
        return durationMinutes;
    }

    public void setDurationMinutes(int minutes) {
        this.durationMinutes = minutes;
    }
}

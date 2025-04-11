package model;
import java.time.LocalDateTime;

public class WorkSession {
    public LocalDateTime startTime;
    public LocalDateTime endTime;
    public int totalSession;

    public WorkSession(int totalSession) {
        this.startTime = LocalDateTime.now();
        this.endTime = null;
        this.totalSession = 0;
    }

    public WorkSession(LocalDateTime start, LocalDateTime end, int duration) {
        this.startTime = start;
        this.endTime = end;
        this.totalSession = duration;

    }

    /*public WorkSession(int manualDuration) {
        this.manualDuration = manualDuration;
        this.startTime = null;
        this.endTime = null;
    }*/

    public int getDuration() {
        endTime = LocalDateTime.now();
        if (startTime == null) {
            throw new IllegalStateException("startTime or endTime is null");
        }
        totalSession = (int) java.time.Duration.between(startTime, endTime).toMinutes();
        return totalSession;

    }

    /*public void setDuration(int duration) {
        this.manualDuration = duration;
    }*/
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
        this.totalSession =(int) java.time.Duration.between(startTime, endTime).toMinutes();
    }

    /*public int getManualDuration() {
        return manualDuration;
    }
    public void setManualDuration(int manualDuration) {
        this.manualDuration = manualDuration;
    }*/

    // Getter - Setter'lar
}

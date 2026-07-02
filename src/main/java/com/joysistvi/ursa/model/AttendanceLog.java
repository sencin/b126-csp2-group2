package com.joysistvi.ursa.model;

import java.time.LocalDateTime;

public class AttendanceLog {

    private final int id;
    private final int studentId;
    private final int scheduleId;
    private final LocalDateTime timestamp;
    private final String action;

    public AttendanceLog(
            int id,
            int studentId,
            int scheduleId,
            LocalDateTime timestamp,
            String action) {
        this.id = id;
        this.studentId = studentId;
        this.scheduleId = scheduleId;
        this.timestamp = timestamp;
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getAction() {
        return action;
    }
}
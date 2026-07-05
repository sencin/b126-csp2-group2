package com.joysistvi.ursa.model;

import java.time.LocalDateTime;

public class AttendanceLog {

    private final int id;
    private final int studentId;
    private final int scheduleId;
    private final LocalDateTime timestamp;
    private final String action;

    // New fields for JOINed data
    private String studentName;
    private String courseTitle;
    private String teacherName;


    // Constructor 1: Used for INSERTs (No names/titles needed)
    public AttendanceLog(int id, int studentId, int scheduleId, LocalDateTime timestamp, String action) {
        this.id = id;
        this.studentId = studentId;
        this.scheduleId = scheduleId;
        this.timestamp = timestamp;
        this.action = action;
    }

    // Constructor 2: Used for SELECT queries with JOINs
    public AttendanceLog(int id, int studentId, String studentName, int scheduleId, String courseTitle,
                         String teacherName, LocalDateTime timestamp, String action) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.scheduleId = scheduleId;
        this.courseTitle = courseTitle;
        this.teacherName = teacherName;
        this.timestamp = timestamp;
        this.action = action;
    }

    // Existing Getters
    public int getId() { return id; }
    public String getTeacherName() { return teacherName; }
    public int getStudentId() { return studentId; }
    public int getScheduleId() { return scheduleId; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getAction() { return action; }

    // New Getters for JOINed data
    public String getStudentName() { return studentName; }
    public String getCourseTitle() { return courseTitle; }
}
package com.joysistvi.ursa.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule {

    private final int id;
    private final int courseId;
    private final String courseTitle;
    private final int teacherId;
    private final String instructorName;
    private final LocalDate date;
    private final LocalTime startTime;
    private final LocalTime endTime;

    // Constructor 1: Used when creating a schedule (ONLY requires IDs, no text names needed)
    public Schedule(int id, int courseId, int teacherId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.courseId = courseId;
        this.courseTitle = null; // Stays null until fetched from DB
        this.teacherId = teacherId;
        this.instructorName = null; // Stays null until fetched from DB
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Constructor 2: Used when fetching a schedule from the database (includes all JOINed data)
    public Schedule(int id, int courseId, String courseTitle, int teacherId, String instructorName, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.teacherId = teacherId;
        this.instructorName = instructorName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
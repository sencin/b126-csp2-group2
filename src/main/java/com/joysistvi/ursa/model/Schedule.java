package com.joysistvi.ursa.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule {

    private final int id;
    private final int courseId;
    private final String courseTitle;
    private final String instructorName;
    private final LocalDate date;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public Schedule(int id, int courseId, String instructorName, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.courseId = courseId;
        this.courseTitle = null;
        this.instructorName = instructorName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Schedule(int id, int courseId, String courseTitle, String instructorName, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.courseId = courseId;
        this.courseTitle = courseTitle;
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

    public String getCourseTitle() {
        return courseTitle;
    }
}
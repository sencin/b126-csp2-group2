package com.joysistvi.ursa.model;

public class Course {

    private final int id;
    private final String courseCode;
    private final String courseTitle;

    public Course(int id, String courseCode, String courseTitle) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
    }

    public int getId() {
        return id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }
}
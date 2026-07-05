package com.joysistvi.ursa.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class StudentSchedule {

    private final int id;
    private final int studentId;
    private final String studentName; // Added
    private final int scheduleId;
    private final String courseCode;  // Added
    private final String courseTitle; // Added
    private final String teacherName; // Added
    private final LocalDate date;     // Added
    private final LocalTime startTime;// Added
    private final LocalTime endTime;  // Added
    private final String academicYear;
    private final String semester;

    // Constructor 1: For creating a new entry (IDs only)
    public StudentSchedule(int id, int studentId, int scheduleId, String academicYear, String semester) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = null;
        this.scheduleId = scheduleId;
        this.courseCode = null;
        this.courseTitle = null;
        this.teacherName = null;
        this.date = null;
        this.startTime = null;
        this.endTime = null;
        this.academicYear = academicYear;
        this.semester = semester;
    }

    // Constructor 2: For fetching complete display data from the DB JOINs
    public StudentSchedule(int id, int studentId, String studentName, int scheduleId,
                           String courseCode, String courseTitle, String teacherName,
                           LocalDate date, LocalTime startTime, LocalTime endTime,
                           String academicYear, String semester) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.scheduleId = scheduleId;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.teacherName = teacherName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.academicYear = academicYear;
        this.semester = semester;
    }

    // Getters for the new fields...
    public int getId() { return id; }
    public int getStudentId() { return studentId; }
    public String getStudentName() { return studentName; }
    public int getScheduleId() { return scheduleId; }
    public String getCourseCode() { return courseCode; }
    public String getCourseTitle() { return courseTitle; }
    public String getTeacherName() { return teacherName; }
    public LocalDate getDate() { return date; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }
    public String getAcademicYear() { return academicYear; }
    public String getSemester() { return semester; }
}
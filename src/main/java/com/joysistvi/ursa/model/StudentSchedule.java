package com.joysistvi.ursa.model;

public class StudentSchedule {

    private final int id;
    private final int studentId;
    private final int scheduleId;
    private final String academicYear;
    private final String semester;

    public StudentSchedule(int id,
                           int studentId,
                           int scheduleId,
                           String academicYear,
                           String semester) {
        this.id = id;
        this.studentId = studentId;
        this.scheduleId = scheduleId;
        this.academicYear = academicYear;
        this.semester = semester;
    }

    public StudentSchedule(int studentId,
                           int scheduleId,
                           String academicYear,
                           String semester) {
        this(0, studentId, scheduleId, academicYear, semester);
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

    public String getAcademicYear() {
        return academicYear;
    }

    public String getSemester() {
        return semester;
    }
}
package com.joysistvi.ursa.model;

public class Guardian {

    private final int id;
    private final int studentId;
    private final String firstName;
    private final String lastName;
    private final String relationship;
    private final String phoneNumber;

    private String studentFullName;

    public Guardian(int id,
                    int studentId,
                    String firstName,
                    String lastName,
                    String relationship,
                    String phoneNumber) {
        this.id = id;
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.relationship = relationship;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRelationship() {
        return relationship;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStudentFullName() {
        return studentFullName;
    }

    public void setStudentFullName(String studentFullName) {
        this.studentFullName = studentFullName;
    }
}
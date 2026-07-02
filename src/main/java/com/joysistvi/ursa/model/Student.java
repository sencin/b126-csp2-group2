package com.joysistvi.ursa.model;

import java.time.LocalDate;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String password;
    private String gender;
    private String accountStatus;
    private int yearLevel;
    private LocalDate dateOfBirth;

    // Constructor for registering a basic student
    public Student(String firstName, String lastName, String email, int yearLevel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.yearLevel = yearLevel;
        this.accountStatus = "Active"; // Default value matching database schema
    }

    // Full Constructor for database retrieval
    public Student(int id, String firstName, String lastName, String middleName, String email,
                   String password, String gender, String accountStatus, int yearLevel, LocalDate dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.accountStatus = accountStatus;
        this.yearLevel = yearLevel;
        this.dateOfBirth = dateOfBirth;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getMiddleName() { return middleName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public String getGender() { return gender; }

    public String getAccountStatus() { return accountStatus; }
    public int getYearLevel() { return yearLevel; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
}

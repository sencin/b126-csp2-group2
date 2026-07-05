package com.joysistvi.ursa.model;

import java.time.LocalDate;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String password;
    private String gender;
    private String accountStatus;
    private String role;
    private LocalDate dateOfBirth;

    // Constructor for creating a basic user
    public User(String firstName, String lastName, String email, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.accountStatus = "Active";
    }

    // Full constructor for database retrieval
    public User(
            int id,
            String firstName,
            String lastName,
            String middleName,
            String email,
            String password,
            String gender,
            String accountStatus,
            String role,
            LocalDate dateOfBirth) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.accountStatus = accountStatus;
        this.role = role;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public String getRole() {
        return role;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}
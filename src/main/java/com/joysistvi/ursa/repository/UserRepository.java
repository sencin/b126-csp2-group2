package com.joysistvi.ursa.repository;

import com.joysistvi.ursa.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {

    void addUser(User user) throws SQLException;

    List<User> getAllUsers() throws SQLException;

    User getUserById(int id) throws SQLException;

    void updateUser(User user) throws SQLException;

    void deleteUser(int id) throws SQLException;

    public User getUserByEmail(String email) throws SQLException;
}
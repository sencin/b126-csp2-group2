package com.joysistvi.ursa.repository;

import com.joysistvi.ursa.model.Guardian;

import java.sql.SQLException;
import java.util.List;

public interface GuardianRepository {
    void addGuardian(Guardian guardian) throws SQLException;
    List<Guardian> getGuardiansByStudentId(int studentId) throws SQLException;
    Guardian getGuardianById(int id) throws SQLException;
    void updateGuardian(Guardian guardian) throws SQLException;
    void deleteGuardian(int id) throws SQLException;
}
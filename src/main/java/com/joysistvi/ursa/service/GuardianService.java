package com.joysistvi.ursa.service;

import com.joysistvi.ursa.model.Guardian;
import com.joysistvi.ursa.repository.GuardianRepository;
import com.joysistvi.ursa.repository.impl.GuardianRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class GuardianService {

    private final GuardianRepository guardianRepository;

    public GuardianService() {
        this.guardianRepository = new GuardianRepositoryImpl();
    }

    public void addGuardian(Guardian guardian) throws SQLException {
        guardianRepository.addGuardian(guardian);
    }

    public List<Guardian> getGuardiansByStudentId(int studentId) throws SQLException {
        return guardianRepository.getGuardiansByStudentId(studentId);
    }

    public Guardian getGuardianById(int id) throws SQLException {
        return guardianRepository.getGuardianById(id);
    }

    public void updateGuardian(Guardian guardian) throws SQLException {
        guardianRepository.updateGuardian(guardian);
    }

    public void deleteGuardian(int id) throws SQLException {
        guardianRepository.deleteGuardian(id);
    }
}
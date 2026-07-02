package com.joysistvi.ursa.service;

import com.joysistvi.ursa.model.AttendanceLog;
import com.joysistvi.ursa.repository.AttendanceLogRepository;
import com.joysistvi.ursa.repository.impl.AttendanceLogRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class AttendanceLogService {

    private final AttendanceLogRepository attendanceLogRepository;

    public AttendanceLogService() {
        this.attendanceLogRepository = new AttendanceLogRepositoryImpl();
    }

    public void addAttendanceLog(AttendanceLog attendanceLog) throws SQLException {
        attendanceLogRepository.addAttendanceLog(attendanceLog);
    }

    public List<AttendanceLog> getAttendanceLogsByScheduleId(int scheduleId) throws SQLException {
        return attendanceLogRepository.getAttendanceLogsByScheduleId(scheduleId);
    }

    public AttendanceLog getAttendanceLogById(int id) throws SQLException {
        return attendanceLogRepository.getAttendanceLogById(id);
    }

    public void updateAttendanceLog(AttendanceLog attendanceLog) throws SQLException {
        attendanceLogRepository.updateAttendanceLog(attendanceLog);
    }

    public void deleteAttendanceLog(int id) throws SQLException {
        attendanceLogRepository.deleteAttendanceLog(id);
    }
}
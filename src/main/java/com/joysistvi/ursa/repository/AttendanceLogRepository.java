package com.joysistvi.ursa.repository;

import com.joysistvi.ursa.model.AttendanceLog;
import com.joysistvi.ursa.model.Schedule;

import java.sql.SQLException;
import java.util.List;

public interface AttendanceLogRepository {

    void addAttendanceLog(AttendanceLog attendanceLog) throws SQLException;

    List<AttendanceLog> getAttendanceLogsByScheduleId(int scheduleId) throws SQLException;

    AttendanceLog getAttendanceLogById(int id) throws SQLException;

    void updateAttendanceLog(AttendanceLog attendanceLog) throws SQLException;

    void deleteAttendanceLog(int id) throws SQLException;
}

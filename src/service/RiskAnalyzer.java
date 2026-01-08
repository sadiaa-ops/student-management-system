package service;

import dao.AttendanceDAO;
import dao.MarksDAO;
import model.Attendance;

public class RiskAnalyzer {

    public boolean isStudentAtRisk(int studentId) {

        AttendanceDAO attendanceDAO = new AttendanceDAO();
        MarksDAO marksDAO = new MarksDAO();

        Attendance attendance = attendanceDAO.getAttendanceByStudentId(studentId);
        double avgMarks = marksDAO.getAverageMarks(studentId);

        if (attendance == null) {
            // No attendance record → considered at risk
            return true;
        }

        if (avgMarks == -1) {
            // No marks record → considered at risk
            return true;
        }


        return attendance.getAttendancePercentage() < 75 || avgMarks < 50;
    }
}

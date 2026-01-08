package dao;

import db.DBConnection;
import model.Attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AttendanceDAO {
	public void saveAttendance(int studentId, int attended, int total) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = """
                INSERT INTO attendance(student_id, classes_attended, total_classes)
                VALUES (?, ?, ?)
                ON CONFLICT (student_id)
                DO UPDATE SET classes_attended = ?, total_classes = ?
            """;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, studentId);
            ps.setInt(2, attended);
            ps.setInt(3, total);
            ps.setInt(4, attended);
            ps.setInt(5, total);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Attendance getAttendanceByStudentId(int studentId) {

        Attendance attendance = null;

        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT classes_attended, total_classes " +
                    "FROM attendance WHERE student_id = " + studentId
            );

            if (rs.next()) {
                attendance = new Attendance(
                        studentId,
                        rs.getInt("classes_attended"),
                        rs.getInt("total_classes")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return attendance;
    }
    
}

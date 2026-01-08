package dao;

import db.DBConnection;
import model.SubjectMark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MarksDAO {

    // 1️⃣ Get average marks (used in RiskAnalyzer & StudentMenu)
    public double getAverageMarks(int studentId) {

        double avgMarks = -1;

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT AVG(marks) AS avg_marks FROM marks WHERE student_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                avgMarks = rs.getDouble("avg_marks");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return avgMarks;
    }

    // 2️⃣ Add marks (Admin feature)
    public void addMarks(int studentId, String subject, int marks) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO marks(student_id, subject, marks) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, studentId);
            ps.setString(2, subject);
            ps.setInt(3, marks);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3️⃣ Get subject-wise marks (Student view)
    public List<SubjectMark> getMarksByStudentId(int studentId) {

        List<SubjectMark> marksList = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT subject, marks FROM marks WHERE student_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                marksList.add(
                    new SubjectMark(
                        rs.getString("subject"),
                        rs.getInt("marks")
                    )
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return marksList;
    }
    
}

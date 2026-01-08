package dao;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthDAO {

    public String loginAndGetRole(int studentId, String password) {

        String role = null;

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT role FROM student WHERE student_id = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, studentId);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                role = rs.getString("role");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return role; // ADMIN / STUDENT / null
    }
}


package dao;

import db.DBConnection;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // 1️⃣ GET ALL STUDENTS (you already had this)
    public List<Student> getAllStudent() {
        List<Student> students = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT student_id, name, email FROM student";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("student_id"),
                        rs.getString("name"),
                        rs.getString("email")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    // 2️⃣ ADD STUDENT
    public void addStudent(int id, String name, String email, String password, String role) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO student VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, role);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3️⃣ UPDATE STUDENT
    public void updateStudent(int id, String name, String email) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "UPDATE student SET name = ?, email = ? WHERE student_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, id);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 4️⃣ DELETE STUDENT
    public void deleteStudent(int id) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "DELETE FROM student WHERE student_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


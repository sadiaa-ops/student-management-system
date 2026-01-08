package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection conn = null;

    private static final String URL =
            "jdbc:postgresql://localhost:5432/student_management";
    private static final String USER = "postgres";
    private static final String PASSWORD = "hellfire666";

    public static Connection getConnection() {

        try {
            if (conn == null) {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connected successfully!");
            }
        } catch (Exception e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }

        return conn;
    }
}

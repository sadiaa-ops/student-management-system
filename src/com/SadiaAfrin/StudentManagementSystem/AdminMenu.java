package com.SadiaAfrin.StudentManagementSystem;

import dao.AttendanceDAO;
import dao.MarksDAO;
import dao.StudentDAO;
import model.Student;
import model.Attendance;
import service.RiskAnalyzer;

import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    private Scanner sc = new Scanner(System.in);

    private StudentDAO studentDAO = new StudentDAO();
    private AttendanceDAO attendanceDAO = new AttendanceDAO();
    private MarksDAO marksDAO = new MarksDAO();
    private RiskAnalyzer riskAnalyzer = new RiskAnalyzer();

    public void showMenu() {

        while (true) {
            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Record Attendance");
            System.out.println("5. Record Marks");
            System.out.println("6. View At-Risk Students");
            System.out.println("7. Performance Summary");
            System.out.println("8. Logout");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> updateStudent();
                case 3 -> deleteStudent();
                case 4 -> recordAttendance();
                case 5 -> recordMarks();
                case 6 -> viewAtRiskStudents();
                case 7 -> performanceSummary();
                case 8 -> {
                    System.out.println("Logging out...");
                    return; // BACK TO MAIN MENU
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void addStudent() {
        System.out.print("Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        studentDAO.addStudent(id, name, email, password, "STUDENT");
        System.out.println("Student added successfully!");
    }

    private void updateStudent() {
        System.out.print("Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("New Name: ");
        String name = sc.nextLine();

        System.out.print("New Email: ");
        String email = sc.nextLine();

        studentDAO.updateStudent(id, name, email);
        System.out.println("Student updated successfully!");
    }

    private void deleteStudent() {
        System.out.print("Student ID: ");
        int id = sc.nextInt();

        studentDAO.deleteStudent(id);
        System.out.println("Student deleted successfully!");
    }

    private void recordAttendance() {
        System.out.print("Student ID: ");
        int id = sc.nextInt();

        System.out.print("Classes Attended: ");
        int attended = sc.nextInt();

        System.out.print("Total Classes: ");
        int total = sc.nextInt();

        attendanceDAO.saveAttendance(id, attended, total);
        System.out.println("Attendance recorded successfully!");
    }

    private void recordMarks() {
        System.out.print("Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Subject: ");
        String subject = sc.nextLine();

        System.out.print("Marks: ");
        int marks = sc.nextInt();

        marksDAO.addMarks(id, subject, marks);
        System.out.println("Marks recorded successfully!");
    }

    private void viewAtRiskStudents() {
        List<Student> students = studentDAO.getAllStudent();

        System.out.println("\n--- AT-RISK STUDENTS ---");

        for (Student s : students) {
            if (riskAnalyzer.isStudentAtRisk(s.getStudentID())) {
                System.out.println(s.getStudentID() + " | " + s.getName());
            }
        }
    }

    private void performanceSummary() {
        List<Student> students = studentDAO.getAllStudent();

        System.out.println("\n--- PERFORMANCE SUMMARY ---");

        for (Student s : students) {
            Attendance att = attendanceDAO.getAttendanceByStudentId(s.getStudentID());
            double avgMarks = marksDAO.getAverageMarks(s.getStudentID());

            double attendancePercent = (att != null)
                    ? att.getAttendancePercentage()
                    : 0;

            String status = riskAnalyzer.isStudentAtRisk(s.getStudentID())
                    ? "AT RISK"
                    : "SAFE";

            System.out.printf(
                    "%s | Attendance: %.2f%% | Avg Marks: %.2f | %s%n",
                    s.getName(),
                    attendancePercent,
                    avgMarks,
                    status
            );
        }
    }
}

package com.SadiaAfrin.StudentManagementSystem;

import dao.AttendanceDAO;
import dao.MarksDAO;
import model.Attendance;
import model.Marks;
import model.SubjectMark;
import service.RiskAnalyzer;

import java.util.List;
import java.util.Scanner;

public class StudentMenu {

    private int studentId;
    private Scanner sc = new Scanner(System.in);

    private AttendanceDAO attendanceDAO = new AttendanceDAO();
    private MarksDAO marksDAO = new MarksDAO();
    private RiskAnalyzer riskAnalyzer = new RiskAnalyzer();

    public StudentMenu(int studentId) {
        this.studentId = studentId;
    }

    public void showMenu() {

        while (true) {
            System.out.println("\n===== STUDENT MENU =====");
            System.out.println("1. View Attendance");
            System.out.println("2. View Marks");
            System.out.println("3. View Status");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> viewAttendance();
                case 2 -> viewMarks();
                case 3 -> viewStatus();
                case 4 -> {
                    System.out.println("Logging out...");
                    return; // BACK TO MAIN MENU
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void viewAttendance() {
        Attendance att = attendanceDAO.getAttendanceByStudentId(studentId);

        if (att == null) {
            System.out.println("Attendance record not found.");
            return;
        }

        System.out.printf("Attendance: %.2f%%%n", att.getAttendancePercentage());
    }

    public void viewMarks() {

        MarksDAO marksDAO = new MarksDAO();

        List<SubjectMark> marksList = marksDAO.getMarksByStudentId(studentId);

        if (marksList.isEmpty()) {
            System.out.println("No marks found.");
            return;
        }

        System.out.println("\n--- Marks ---");
        int total = 0;

        for (SubjectMark sm : marksList) {
            System.out.println(sm.getSubject() + ": " + sm.getMarks());
            total += sm.getMarks();
        }

        double average = (double) total / marksList.size();
        System.out.println("Average Marks: " + average);
    }


    private void viewStatus() {
        boolean atRisk = riskAnalyzer.isStudentAtRisk(studentId);
        System.out.println("Status: " + (atRisk ? "AT RISK" : "SAFE"));
    }
}

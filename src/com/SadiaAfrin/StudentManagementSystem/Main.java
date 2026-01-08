package com.SadiaAfrin.StudentManagementSystem;
import java.util.Scanner;
import dao.AuthDAO;
import com.SadiaAfrin.StudentManagementSystem.AdminMenu;
import com.SadiaAfrin.StudentManagementSystem.StudentMenu;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AuthDAO authDAO = new AuthDAO();

        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 2) {
                System.out.println("Exiting system...");
                break; // EXIT CONSOLE
            }

            if (choice != 1) {
                System.out.println("Invalid choice!");
                continue;
            }

            // LOGIN FLOW
            System.out.print("ID: ");
            int studentId = sc.nextInt();
            sc.nextLine();

            System.out.print("Password: ");
            String password = sc.nextLine();

            String role = authDAO.loginAndGetRole(studentId, password);

            if (role == null) {
                System.out.println("Invalid credentials!");
                continue;
            }

            System.out.println("Login Successful! Role: " + role);

            // ROLE BASED MENU
            if (role.equalsIgnoreCase("ADMIN")) {
                AdminMenu adminMenu = new AdminMenu();
                adminMenu.showMenu();   // returns on logout
            } else {
                StudentMenu studentMenu = new StudentMenu(studentId);
                studentMenu.showMenu(); // returns on logout
            }
        }

        sc.close();
    }
}

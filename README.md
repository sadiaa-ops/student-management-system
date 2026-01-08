# Student Management System (Java + PostgreSQL)

A robust console-based Student Management System built with Java, JDBC, and PostgreSQL. This project demonstrates professional software architecture using DAO and Service-layer patterns, implementing role-based access control and intelligent academic risk analysis.

---

## Project Overview

Unlike simple CRUD applications, this system incorporates **business logic** for academic performance monitoring, making it suitable for real-world educational institutions. The system automatically identifies at-risk students based on attendance and academic performance metrics.

---

## Key Features

### Role-Based Access Control
- **Admin Role**: Full system access with management capabilities
- **Student Role**: Personal academic data viewing with read-only access

### Admin Capabilities
- **Student Management**: Add, update, and delete student records
- **Attendance Tracking**: Record and monitor class attendance
- **Grade Management**: Record marks across multiple subjects
- **Risk Monitoring**: View comprehensive list of at-risk students
- **Analytics**: Generate detailed performance summaries
- **Secure Logout**: Session management with secure exit

### Student Capabilities
- **Secure Authentication**: Password-protected login system
- **Attendance Overview**: View personal attendance percentage
- **Grade Report**: Access marks for all subjects with calculated averages
- **Academic Status**: Real-time risk assessment (SAFE/AT RISK)

### Academic Risk Analysis
A student is flagged as **AT RISK** if:
- **Attendance < 75%** OR
- **Average Marks < 50**

> **Architecture Note**: Risk logic is encapsulated in the **Service Layer** (`RiskAnalyzer`), maintaining separation from data access operations.

---

## Architecture Overview

This project follows **layered architecture** (MVC-inspired) with clear separation of concerns:

```
┌─────────────────────────────────────────┐
│         Presentation Layer              │
│  (AdminMenu / StudentMenu)              │
│  - User interaction & console UI        │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│         Service Layer                   │
│  (RiskAnalyzer, Business Logic)         │
│  - Academic risk calculation            │
│  - Performance analysis                 │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│         Data Access Layer (DAO)         │
│  - StudentDAO                           │
│  - MarksDAO                             │
│  - AttendanceDAO                        │
│  - AuthDAO                              │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│         Database Layer                  │
│  (DBConnection)                         │
│  - Connection pooling                   │
│  - JDBC management                      │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│         PostgreSQL Database             │
└─────────────────────────────────────────┘
```

### Architecture Flow
```
User Input → Menu Layer → Service Layer → DAO Layer → Database → Response
```

---

## Database Schema

### **`student`** Table
| Column      | Type                         | Constraints |
|-------------|------------------------------|-------------|
| student_id  | INT                          | PRIMARY KEY |
| name        | VARCHAR(100)                 | NOT NULL    |
| email       | VARCHAR(100)                 | UNIQUE      |
| password    | VARCHAR(255)                 | NOT NULL    |
| role        | VARCHAR(20)                  | CHECK (role IN ('ADMIN', 'STUDENT')) |

### **`attendance`** Table
| Column           | Type | Constraints |
|------------------|------|-------------|
| attendance_id    | INT  | PRIMARY KEY |
| student_id       | INT  | FOREIGN KEY → student(student_id) |
| classes_attended | INT  | NOT NULL    |
| total_classes    | INT  | NOT NULL    |

### **`marks`** Table
| Column     | Type         | Constraints |
|------------|--------------|-------------|
| marks_id   | INT          | PRIMARY KEY |
| student_id | INT          | FOREIGN KEY → student(student_id) |
| subject    | VARCHAR(50)  | NOT NULL    |
| marks      | INT          | CHECK (marks BETWEEN 0 AND 100) |

---

## Technologies & Tools

| Category | Technology |
|----------|-----------|
| **Language** | Java (JDK 8+) |
| **Database** | PostgreSQL 12+ |
| **Connectivity** | JDBC Driver |
| **Architecture** | DAO Pattern, Service Layer |
| **UI** | Console-based (Scanner) |
| **Design Patterns** | Singleton (DBConnection), DAO, MVC |

---
## Project Structure

```
student-management-system/
├── src/
│   ├── Main.java
│   ├── model/
│   │   ├── Student.java
│   │   ├── Attendance.java
│   │   └── Marks.java
│   ├── dao/
│   │   ├── StudentDAO.java
│   │   ├── AttendanceDAO.java
│   │   ├── MarksDAO.java
│   │   └── AuthDAO.java
│   ├── service/
│   │   └── RiskAnalyzer.java
│   ├── menu/
│   │   ├── AdminMenu.java
│   │   └── StudentMenu.java
│   └── db/
│       └── DBConnection.java
├── lib/
│   └── postgresql-jdbc.jar
├── schema.sql
└── README.md
```

---

## Getting Started

### Prerequisites
- Java Development Kit (JDK 8 or higher)
- PostgreSQL (version 12+)
- PostgreSQL JDBC Driver
- Git

### Installation Steps

1. **Clone the Repository**
   ```bash
   git clone https://github.com/sadiaa-ops/student-management-system.git
   cd student-management-system
   ```

2. **Set Up Database**
   ```sql
   -- Create database
   CREATE DATABASE student_management_db;
   
   -- Connect to database
   \c student_management_db
   
   -- Run the schema.sql file (create tables)
   \i schema.sql
   ```

3. **Configure Database Connection**
   
   Update `src/db/DBConnection.java` with your credentials:
   ```java
   private static final String URL = "jdbc:postgresql://localhost:5432/student_management_db";
   private static final String USER = "your_username";
   private static final String PASSWORD = "your_password";
   ```

4. **Compile and Run**
   ```bash
   # Compile
   javac -cp ".:postgresql-jdbc.jar" src/Main.java
   
   # Run
   java -cp ".:postgresql-jdbc.jar" Main
   ```

---

## Sample Output

```
===== Student Management System =====
1. Login
2. Exit
Enter choice: 1
ID: 201
Password: pass123
Database connected successfully!
Login Successful! Role: ADMIN

===== ADMIN MENU =====
1. Add Student
2. Update Student
3. Delete Student
4. Record Attendance
5. Record Marks
6. View At-Risk Students
7. Performance Summary
8. Logout
Enter choice: 7

--- PERFORMANCE SUMMARY ---
Emily Johnson | Attendance: 75.00% | Avg Marks: 68.50 | SAFE
Michael Scott | Attendance: 55.00% | Avg Marks: 42.50 | AT RISK
Sophia Brown | Attendance: 95.00% | Avg Marks: 89.50 | SAFE
Mavis Grey | Attendance: 65.00% | Avg Marks: 50.00 | AT RISK
Adam West | Attendance: 87.50% | Avg Marks: 79.00 | SAFE
Sarah Cook | Attendance: 0.00% | Avg Marks: 0.00 | AT RISK
```

---

## Future Enhancements

- **GUI Development**: Migrate to Swing or JavaFX for modern desktop UI
- **Security**: Implement BCrypt password hashing
- **Reporting**: Export performance reports to PDF/CSV formats
- **Analytics Dashboard**: Visual charts using JFreeChart
- **Email Notifications**: Alert at-risk students automatically
- **REST API**: Convert to Spring Boot microservice
- **Multi-semester Support**: Historical data tracking
- **Backup & Restore**: Automated database backup functionality

---




##### **Student Management System (Java + PostgreSQL)**

A console-based Student Management System built using Java, JDBC, and PostgreSQL, designed with DAO and Service-layer architecture.

The system supports role-based access (Admin \& Student) and includes academic risk analysis, making it more than a simple CRUD application.



###### **Features:**

**User Roles:**

* Admin
* Student



###### **Admin Capabilities:**

* Add/Update/Delete students
* Record student attendance
* Record student marks
* View list of At-Risk Students
* Generate Performance Summary
* Logout securely



###### **Student Capabilities:**

* Login securely
* View attendance percentage
* View mark (all subjects + average)
* View academic status (SAFE/AT RISK)



###### **Academic Risk Logic:**

A student is considered AT RISK if:

Attendance<75% OR Average Marks <50

This logic is implemented in a dedicated service layer, not directly in DAO classes.



###### **Architecture Overview:**

This project follows layered (MVC-inspired) architecture with clear separation of concerns:

Menu layer -> User interaction (console UI)

Service layer -> Business logic

DAO layer -> Database operations

Model layer -> Data representation

DB layer -> Database connectivity

Main class -> Application entry point



**Architecture Flow (How everything connects)**

User

&nbsp;↓

Menu (AdminMenu / StudentMenu)

&nbsp;↓

Service (RiskAnalyzer)

&nbsp;↓

DAO (StudentDAO, MarksDAO, AttendanceDAO, AuthDAO)

&nbsp;↓

DBConnection

&nbsp;↓

Database



###### **Database Design (PostgreSQL):**



**Tables:**



**student**

Column          | Type

----------------|-------------------------

student\_id      | INT (PK)

name            | VARCHAR

email           | VARCHAR

password        | VARCHAR

role            | VARCHAR (ADMIN / STUDENT)





**attendance**

Column            | Type

------------------|----------------

attendance\_id     | INT (PK)

student\_id        | INT (FK)

classes\_attended  | INT

total\_classes     | INT





**marks**

Column            | Type

------------------|----------------

attendance\_id     | INT (PK)

student\_id        | INT (FK)

classes\_attended  | INT

total\_classes     | INT





###### **Tools Used:**

* Java (JDK 8+)
* PostgreSQL
* JDBC
* SQL
* Console UI
* DAO Design Pattern



###### **How to Run Locally:**

* Clone the repository: https://github.com/sadiaa-ops/student-management-system.git
* Create PostgreSQL database and tables
* Update database credentials in DBConnection.java
* Run Main.java



###### **Sample Output:**

===== Student Management System =====

1\. Login

2\. Exit

Enter choice: 1

ID: 201

Password: pass123

Database connected successfully!

Login Successful! Role: ADMIN



===== ADMIN MENU =====

1\. Add Student

2\. Update Student

3\. Delete Student

4\. Record Attendance

5\. Record Marks

6\. View At-Risk Students

7\. Performance Summary

8\. Logout

Enter choice: 8

Logging out...



###### **Future Enhancements:**

* Swing/JavaFX UI
* Password hashing
* Export reports (PDF/CSV)
* Web version using Spring Boot








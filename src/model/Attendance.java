package model;

public class Attendance {

    private int studentId;
    private int classesAttended;
    private int totalClasses;

    public Attendance(int studentId, int classesAttended, int totalClasses) {
        this.studentId = studentId;
        this.classesAttended = classesAttended;
        this.totalClasses = totalClasses;
    }

    public int getStudentId() {
        return studentId;
    }

    public double getAttendancePercentage() {
        if (totalClasses == 0) return 0;
        return (classesAttended * 100.0) / totalClasses;
    }
}

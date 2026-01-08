package model;

public class SubjectMark {

    private String subject;
    private int marks;

    public SubjectMark(String subject, int marks) {
        this.subject = subject;
        this.marks = marks;
    }

    public String getSubject() {
        return subject;
    }

    public int getMarks() {
        return marks;
    }
}

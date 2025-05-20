package org.example;

import jakarta.persistence.*;
//import javax.persistence.*;

import java.io.*;

@Embeddable
public class LinkedPurchaseKey implements Serializable {
    @Column(name = "student_id")
    private int studentID;
    @Column(name = "course_id")
    private int courseID;

    public LinkedPurchaseKey(int studentID, int courseID) {
        this.studentID = studentID;
        this.courseID = courseID;
    }
    private LinkedPurchaseKey() { }

    public int getStudentID() { return studentID; }
    public void setStudentID(int studentID) { this.studentID = studentID; }
    public int getCourseID() { return courseID; }
    public void setCourseID(int courseID) { this.courseID = courseID; }
}
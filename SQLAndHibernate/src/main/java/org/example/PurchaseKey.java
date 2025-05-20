package org.example;

import jakarta.persistence.*;
//import javax.persistence.*;

import java.io.*;
import java.util.*;

@Embeddable
public class PurchaseKey implements Serializable {
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "course_name")
    private String courseName;

    public PurchaseKey(String studentName, String courseName) {
        this.studentName = studentName;
        this.courseName = courseName;
    }
    private PurchaseKey() { }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
}
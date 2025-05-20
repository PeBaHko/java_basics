package org.example;

import jakarta.persistence.*;
//import javax.persistence.*;

import java.io.*;

@Entity
@Table(name = "linked_purchase_list")
public class LinkedPurchase implements Serializable {
    @EmbeddedId
    private LinkedPurchaseKey key;
    @Column(name = "student_id", insertable = false, updatable = false)
    private int studentID;
    @Column(name = "course_id", insertable = false, updatable = false)
    private int courseID;

    public LinkedPurchase() { }

    public LinkedPurchaseKey getKey() { return key; }
    public void setKey(LinkedPurchaseKey key) { this.key = key; }
    public int getStudentID() { return studentID; }
    public void setStudentID(int studentID) { this.studentID = studentID; }
    public int getCourseID() { return courseID; }
    public void setCourseID(int courseID) { this.courseID = courseID; }
}
package org.example;

import jakarta.persistence.*;
//import javax.persistence.*;


import java.io.*;
import java.util.*;

@Entity
@Table(name = "subscriptions")
public class Subscription implements Serializable {
    @EmbeddedId
    private SubscriptionKey key;
    @Column(name = "student_id", insertable = false, updatable = false)
    private int studentID;
    @Column(name = "course_id", insertable = false, updatable = false)
    private int courseID;
    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public Subscription() { }

    public SubscriptionKey getKey() { return key; }
    public void setKey(SubscriptionKey key) { this.key = key; }
    public int getStudentID() { return studentID; }
    public void setStudentID(int studentID) { this.studentID = studentID; }
    public int getCourseID() { return courseID; }
    public void setCourseID(int courseID) { this.courseID = courseID; }
    public Date getSubscriptionDate() { return subscriptionDate; }
    public void setSubscriptionDate(Date subscriptionDate) { this.subscriptionDate = subscriptionDate; }
}
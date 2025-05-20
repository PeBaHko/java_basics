package org.example;

import jakarta.persistence.*;
//import javax.persistence.*;

import java.io.*;
import java.util.*;

@Entity
@Table(name = "purchase_list")
public class Purchase implements Serializable {
    @EmbeddedId
    private PurchaseKey key;
    @Column(name = "student_name", insertable = false, updatable = false)
    private String studentName;
    @Column(name = "course_name", insertable = false, updatable = false)
    private String courseName;
    private int price;
    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public Purchase() { }

    public PurchaseKey getKey() { return key; }
    public void setKey(PurchaseKey key) { this.key = key; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public Date getSubscriptionDate() { return subscriptionDate; }
    public void setSubscriptionDate(Date subscriptionDate) { this.subscriptionDate = subscriptionDate; }
}
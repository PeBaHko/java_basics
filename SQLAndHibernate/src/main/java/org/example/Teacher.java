package org.example;

import jakarta.persistence.*;
//import javax.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "salary")
    private int salary;
    @Column(name = "age")
    private int age;

    //public Teacher() { }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getSalary() { return salary; }
    public void setSalary(int salary) { this.salary = salary; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
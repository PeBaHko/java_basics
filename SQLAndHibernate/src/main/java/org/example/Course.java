package org.example;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
//import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "duration")
    private int duration;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "enum('DESIGN', 'PROGRAMMING', 'MARKETING', 'MANAGEMENT', 'BUSINESS')")
    private Type type;
    //private enum Type { DESIGN, PROGRAMMING, MARKETING, MANAGEMENT, BUSINESS }
    @Column(name = "description")
    private String description;
    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;
    @Column(name = "students_count", nullable = true)
    private Integer studentsCount;
    @Column(name = "price")
    private int price;
    @Column(name = "price_per_hour")
    private float pricePerHour;

    public Course() { }
    //@Id
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Teacher getTeacher() { return teacher; }
    //public int getTeacherID() { return teacherID; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }
    //public void setTeacherID(int teacherID) { this.teacherID = teacherID; }
    public int getStudentsCount() { return studentsCount; }
    public void setStudentsCount(int studentsCount) { this.studentsCount = studentsCount; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public float getPricePerHour() { return pricePerHour;    }
    public void setRicaPerHour(float pricePerHour) { this.pricePerHour = pricePerHour; }
}
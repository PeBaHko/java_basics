package org.example;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;
import static java.lang.System.out;
public class Work {

    public Work() { }

    public List<Course> getCourseLIst(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        query.select(root);
        List<Course> list = session.createQuery(query).getResultList();
        return new ArrayList<>(list);
    }

    public List<Student> getStudentList(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Student> query = builder.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);
        query.select(root);
        List<Student> list = session.createQuery(query).getResultList();
        return  new ArrayList<>(list);
    }

    public List<Purchase> getPurchaseList(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Purchase> query = builder.createQuery(Purchase.class);
        Root<Purchase> root = query.from(Purchase.class);
        query.select(root);
        List<Purchase> list = session.createQuery(query).getResultList();
        return new ArrayList<>(list);
    }

    public List<LinkedPurchase> getLinkedPurchaeList(List<Course> courses, List<Student> students, List<Purchase> purchases) {
        List<LinkedPurchase> list = new ArrayList<>();
        LinkedPurchase linkedPurchase;
        int courseID = 0;
        String courseName;
        int studentID = 0;
        String studentName;
        for (Purchase purchase : purchases) {
            courseName = purchase.getCourseName();
            studentName = purchase.getStudentName();
            linkedPurchase = new LinkedPurchase();
            for (Course course : courses) {
                if (course.getName().equalsIgnoreCase(courseName)) {
                    courseID = course.getId();
                    linkedPurchase.setCourseID(courseID);
                }
            }
            for (Student student : students) {
                if (student.getName().equalsIgnoreCase(studentName)) {
                    studentID = student.getId();
                    linkedPurchase.setStudentID(studentID);
                }
            }
            linkedPurchase.setKey(new LinkedPurchaseKey(studentID, courseID));
            list.add(linkedPurchase);
        }
        return new ArrayList<>(list);
    }

    public void createTableLinkedPurchase(List<LinkedPurchase> list, Session session) {
        try {
            for (LinkedPurchase purchase : list) {
            String hql = "insert " +
                    "LinkedPurchase (courseID, studentID)" +
                    "values (" +
                    purchase.getCourseID() + ", " +
                    purchase.getStudentID() + ")";

                session.beginTransaction();
                session.createQuery(hql).executeUpdate();
                session.getTransaction().commit();
            }
        } catch (ConstraintViolationException e) { e.printStackTrace(); }
    }




    public void setQuery(Session session) {
        try {
        session.beginTransaction();
        String hql = "insert " +
                "LinkedPurchase (courseID, studentID)" +
                "values (0, 0)";
        int i = session.createQuery(hql).executeUpdate();
        session.getTransaction().commit();
        out.println("добавлено строк - " + i);
        } catch (ConstraintViolationException e) {
            out.println("ошибка добавления");
            e.printStackTrace();
        }
    }
    public void printCourse(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        query.select(root);
        List<Course> list = session.createQuery(query).getResultList();
        for(Course c : list) {
            out.println(c.getId() + " - " + c.getName());
        }
    }
}
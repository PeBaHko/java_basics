package org.example;

import java.sql.*;
import java.time.*;
import java.util.*;

import org.hibernate.*;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;

import static java.lang.System.*;

public class App
{
    public static void main( String[] args )
    {
        String choice;
        String regex = "[012]";
        Scanner scanner = new Scanner(in);
        while(true) {
            out.print("Раздел 20: БД" + System.lineSeparator() +
                        "\tЗадание 1" + System.lineSeparator() +
                        "\tЗадание 2" + System.lineSeparator() +
                        "\tВыход - 0" + System.lineSeparator() +
                        "\t -> ");
            choice = scanner.nextLine();
            if (!choice.matches(regex)) { out.println("ERROR 1"); continue; };
            if(choice.equalsIgnoreCase("0")) { break; }
            switch (choice) {
                case "1" -> ex1();
                case "2" -> ex2();
                default -> out.println("ERROR 2");
            }
        }
    }
    private static void ex2() {
        out.println("Hibernate");

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Work work = new Work();
        List<Course> courses = work.getCourseLIst(session);
        List<Student> students = work.getStudentList(session);
        List<Purchase> purchases = work.getPurchaseList(session);
        List<LinkedPurchase> linkedPurchases = work.getLinkedPurchaeList(courses, students, purchases);

        work.createTableLinkedPurchase(linkedPurchases, session);

        // пример получения строки из таблицы
        // Course course = session.get(Course.class, 1);
        out.println("создание сессии через хипернат");

        session.close();
        sessionFactory.close();
        registry.close();
        out.println("закрытие хипернат");
    }

    private static void ex1() {
        // список курсов
        ArrayList<String> courses;// = new ArrayList<>();
        try {
            // подключение к БД
            ConnectDB connDB = new ConnectDB("skillbox");
            Filter filter = new Filter(connDB.getStatement());
             // получение списка курсов
            ResultSet result = filter.select("name","courses");
            courses = filter.listCourses(result);
            ArrayList<LocalDate> ldl;
            float average;
            for (String course : courses) {
                ldl = filter.getBuyList(course);
                if (!ldl.isEmpty()) {
                    out.println("Название курса: \"" + course + "\"");
                    average = filter.average(ldl);
                    out.println("Среднее количество покупок курса за месяц - " + average);
                } out.println();
            }
            result.close();
            connDB.disconnect();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
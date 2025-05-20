package org.example;

import java.sql.*;
import java.time.*;
import java.util.*;

public class Filter {
    private final Statement statement;
    public Filter (Statement statement) { this.statement = statement; }
    public ResultSet buyCourseList (String sql) throws SQLException {
        return statement.executeQuery(sql);
    }
    public ArrayList<String> listCourses (ResultSet result) throws SQLException {
        ArrayList<String> courses = new ArrayList<>();
        while (result.next()) {
            courses.add(result.getString("name"));
        } return courses;
    }
    public ResultSet select(String field, String table) {
        try {
            return statement.executeQuery("SELECT " + field + " FROM " + table);
        } catch (SQLException e) { return null; }
    }
    public ArrayList<LocalDate> getBuyList (String course) throws SQLException {
        LocalDate ld;
        ArrayList<LocalDate> ldl = new ArrayList<>();
        ResultSet result = buyCourseList("SELECT subscription_date " +
                "FROM purchaselist " +
                "WHERE course_name = \"" + course + "\" ORDER BY subscription_date");
        while (result.next()) {
            ld = LocalDate.parse(result.getString("subscription_date").split(" ")[0]);
            ldl.add(ld);
        }
        result.close();
        return ldl;
    }
    public float average (ArrayList<LocalDate> ldl) {
        LocalDate ldFirst = ldl.get(0);
        LocalDate ldLast = ldl.get(ldl.size()-1);
        int countMount = ldLast.getMonthValue() - ldFirst.getMonthValue() + 1;
        return  ((float) ldl.size() / (float) countMount);
    }
}
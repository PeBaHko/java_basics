package org.example;

import java.sql.*;

public class ConnectDB {
    private final Connection connection;
    private final Statement statement;

    public ConnectDB(String base) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/" + base + "?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "slsds23#.jd";
        connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
    }
    public Statement getStatement() { return statement; }
    public void disconnect() {
        try {
            if (connection != null) {
                statement.close();
                connection.close();
               }
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
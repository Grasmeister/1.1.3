package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String USER_NAME = "root";
    private static final String PASSWORD = "password";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/schema_user";

    public Connection getConnect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
//            System.out.println("Connection in util: "  + connection);
        } catch (
                SQLException ex) {
            System.out.println("Нет соединения!!");
        }
        return connection;
    }


    // реализуйте настройку соеденения с БД
}

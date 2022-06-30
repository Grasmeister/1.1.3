package jm.task.core.jdbc.util;

import com.fasterxml.classmate.AnnotationConfiguration;
import com.mysql.cj.Session;
import com.mysql.cj.xdevapi.SessionFactory;
import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;

import java.lang.module.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static final String USER_NAME = "root";
    private static final String PASSWORD = "password";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/schema_user";

    public static Connection getConnect() {
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

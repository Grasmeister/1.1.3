package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import jm.task.core.jdbc.model.User;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String USER_NAME = "root";
    private static final String PASSWORD = "password";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/schema_user";
    private static SessionFactory sessionFactory;

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

//    public static SessionFactory getSession() {
//        if (sessionFactory == null) {
//            try {
//                Configuration configuration = new Configuration();
//
//                Properties properties = new Properties();
//                properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
//                properties.put(Environment.URL, CONNECTION_URL);
//                properties.put(Environment.USER, USER_NAME);
//                properties.put(Environment.PASS, PASSWORD);
//                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
////                properties.put(Environment.SHOW_SQL, "true");
//                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//                properties.put(Environment.HBM2DDL_AUTO, "update");
//
//                configuration.setProperties(properties);
//                configuration.addAnnotatedClass(User.class);
//                sessionFactory = configuration.buildSessionFactory();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//
//        }
//        return sessionFactory;
//    }


    // реализуйте настройку соеденения с БД
}

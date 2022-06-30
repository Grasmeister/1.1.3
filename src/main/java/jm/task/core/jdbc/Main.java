package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class Main {
    private static final String testName = "Ivan";
    private static final String testLastName = "Ivanov";
    private static final byte testAge = 5;

    public static void main(String[] args) throws SQLException {
        UserDaoJDBCImpl udi = new UserDaoJDBCImpl();
        UserDaoHibernateImpl udhi = new UserDaoHibernateImpl();
        User user = new User("Ivan", "Taranenko", (byte) 2);
        User user2 = new User("Roman", "Ivanov", (byte) 30);
        User user3 = new User("Petr", "Perviy", (byte) 65);
        User user4 = new User("Ira", "Sidorova", (byte) 15);

        udi.dropUsersTable();
        udi.createUsersTable();
        udi.saveUser(user.getName(), user.getLastName(), user.getAge());
        udi.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        udi.saveUser(user3.getName(), user3.getLastName(), user4.getAge());
        udi.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        udi.removeUserById(2);
        List<User> userList = udi.getAllUsers();

        for (User u :
                userList) {
            System.out.println(u.toString());
        }

        udi.cleanUsersTable();
        udi.dropUsersTable();

        System.out.println("UserDaoJDBCImpl закончил!");
        // Проверка хибернейт

        udhi.dropUsersTable();
        udhi.createUsersTable();
        udhi.saveUser(user.getName(), user.getLastName(), user.getAge());
        udhi.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        udhi.saveUser(user3.getName(), user3.getLastName(), user4.getAge());
        udhi.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        udhi.removeUserById(2);
        List<User> userList1 = udhi.getAllUsers();

        for (User u :
                userList1) {
            System.out.println(u.toString());
        }

        udhi.cleanUsersTable();

        udhi.dropUsersTable();
        // реализуйте алгоритм здесь
    }
}

package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import static jm.task.core.jdbc.util.Util.getConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = getConnect();
    /**
     * Получение соединения с БД.
     */
//    private final Util util = new Util();
    /**
     * SQL-запрос для создания таблицы.
     */
    private static final String QUERY_CREAT_TABLE = "CREATE TABLE if not exists users_db("
            + " id int NOT NULL AUTO_INCREMENT,"
            + "name VARCHAR(32) NOT NULL,"
            + "last_name VARCHAR(32) NOT NULL,"
            + "age INT NOT NULL,"
            + "PRIMARY KEY (id));";
    /**
     * SQL-запрос для удаления таблицы.
     */
    private static final String QUERY_DROP_TABLE = "drop table IF EXISTS users_db";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        System.out.println("Создание таблицы");
        try (PreparedStatement statement = connection.prepareStatement(QUERY_CREAT_TABLE)) {
            statement.executeUpdate();
            System.out.println("Таблица  users_db создана.");
        } catch (SQLException ex) {
            System.out.println("Ошибка соединения!");
        }

    }

    public void dropUsersTable() {
        System.out.println("Удаление таблицы");
        try (PreparedStatement statement = connection.prepareStatement(QUERY_DROP_TABLE)) {
            statement.executeUpdate();
            System.out.println("Таблица  users_db удалена.");
        } catch (SQLException ignore) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String queryForLink = String.format("insert  into users_db (name, last_name, age) values (?, ?, ?)");
        try (PreparedStatement statement = connection.prepareStatement(queryForLink)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException ignore) {
        }
    }

    public void removeUserById(long id) {

        try (PreparedStatement statement = connection
                .prepareStatement("delete from users_db where id = ?")) {
            statement.setString(1, String.valueOf(id));
            statement.executeUpdate();
        } catch (SQLException ignore) {
        }

        System.out.println("Пользователь удален.");
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "select * from users_db";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                byte age = resultSet.getByte(4);

                User user = new User(name, lastName, age);
                user.setId(id);
                userList.add(user);
            }
        } catch (SQLException ignore) {
        }
        return userList;
    }

    public void cleanUsersTable() {
        System.out.println("Удаление данных из базы данных: ");
        String queryClearDB = "delete from users_db";
        try (PreparedStatement statement = connection.prepareStatement(queryClearDB)) {
            statement.executeUpdate();
        } catch (SQLException ignore) {
        }
        System.out.println("Данные удалены.");
    }
}

package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    /**
     * Получение соединения с БД.
     */
    private final Util util = new Util();
    /**
     * SQL-запрос для создания таблицы.
     */
    private static final String QUERY_CREAT_TABLE = "CREATE TABLE if not exists users_db("
            + " id int NOT NULL AUTO_INCREMENT,"
            + "name VARCHAR(45) NOT NULL,"
            + "last_name VARCHAR(45) NOT NULL,"
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
        try (Statement statement = util.getConnect().createStatement()) {
            statement.executeUpdate(QUERY_CREAT_TABLE);
            System.out.println("Таблица  users_db создана.");
        } catch (SQLException ex) {
            System.out.println("Ошибка соединения!");
        }

    }

    public void dropUsersTable() {
        System.out.println("Удаление таблицы");
        try (Statement statement = util.getConnect().createStatement()) {
            statement.executeUpdate(QUERY_DROP_TABLE);
            System.out.println("Таблица  users_db удалена.");
        } catch (SQLException ignore) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = util.getConnect().createStatement()) {
            String queryForLink = String.format("INSERT INTO users_db (name, last_name, age)  "
                    + "VALUES('%s', '%s', '%s')", name, lastName, age);
            statement.executeUpdate(queryForLink);
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException ignore) {
        }
    }

    public void removeUserById(long id) {

        try (Statement statement = util.getConnect().createStatement()) {
            String queryRemoveUserById = "delete from users_db where id = '" + id + "'";
            statement.executeUpdate(queryRemoveUserById);
        } catch (SQLException ignore) {
        }

        System.out.println("Пользователь удален.");
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "select * from users_db";
        try (Statement statement = util.getConnect().createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

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
        try (Statement statement = util.getConnect().createStatement()) {
            statement.executeUpdate(queryClearDB);
        } catch (SQLException ignore) {
        }
        System.out.println("Данные удалены.");
    }
}

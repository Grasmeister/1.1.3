package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {


//        UserDaoJDBCImpl ud = new UserDaoJDBCImpl();
    UserDaoHibernateImpl ud = new UserDaoHibernateImpl();


    public void createUsersTable() {
        ud.createUsersTable();
    }

    /**
     * Удаление таблицы из базы.
     */
    public void dropUsersTable() {
        ud.dropUsersTable();

    }

    /**
     * Добавление пользователя в таблицу Users.
     */
    public void saveUser(String name, String lastName, byte age) {
        ud.saveUser(name, lastName, age);
    }

    /**
     * Удаление пользователя по id.
     *
     * @param id
     */
    public void removeUserById(long id) {

        ud.removeUserById(id);
    }

    /**
     * Получение всех пользователей из базы.
     *
     * @return
     */
    public List<User> getAllUsers() {
        return ud.getAllUsers();
    }

    /**
     * Удаление данных из таблицы.
     */
    public void cleanUsersTable() {
        ud.cleanUsersTable();

    }
}

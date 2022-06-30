package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static jm.task.core.jdbc.util.Util.getSession;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    /**
     * SQL-запрос для создания таблицы.
     */
    private static final String QUERY_CREAT_TABLE = "CREATE TABLE IF NOT EXISTS users_db("
            + " id int NOT NULL AUTO_INCREMENT,"
            + "name VARCHAR(32) NOT NULL,"
            + "last_name VARCHAR(32) NOT NULL,"
            + "age INT NOT NULL,"
            + "PRIMARY KEY (id));";
    /**
     * SQL-запрос для удаления таблицы.
     */
    private static final String QUERY_DROP_TABLE = "DROP TABLE IF EXISTS users_db";

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = getSession().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(QUERY_CREAT_TABLE);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = getSession().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(QUERY_DROP_TABLE).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try (Session session = getSession().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = getSession().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = getSession().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            users = (List<User>) session.createQuery("from users_db").list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {

    }
}

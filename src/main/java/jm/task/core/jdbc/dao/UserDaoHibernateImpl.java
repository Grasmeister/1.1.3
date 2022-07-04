package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static jm.task.core.jdbc.util.Util.getSession;

public class UserDaoHibernateImpl implements UserDao {
    /**
     * SQL-запрос для создания таблицы.
     */
    private static final String QUERY_CREAT_TABLE = "CREATE TABLE `schema_user`.`users_db` ("
            + "`id` BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,"
            + "`name` VARCHAR(45) NOT NULL,"
            + "`last_name` VARCHAR(45) NOT NULL,"
            + "`age` TINYINT NOT NULL);";
    /**
     * SQL-запрос для удаления таблицы.
     */
    private static final String QUERY_DROP_TABLE = "DROP TABLE IF EXISTS users_db";

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = getSession().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(QUERY_CREAT_TABLE).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }


    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = getSession().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(QUERY_DROP_TABLE).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = getSession().openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = getSession().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> users = null;
        try (Session session = getSession().openSession()) {
            transaction = session.beginTransaction();
            users = session.createQuery("From " + User.class.getSimpleName()).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = getSession().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("delete from users_db").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}

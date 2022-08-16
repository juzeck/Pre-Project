package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try(Statement stat = Util.getConnection()) {
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS User (Id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age INT)");

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable() {
        try(Statement stat = Util.getConnection()) {
            stat.executeUpdate("drop table IF EXISTS User");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        tx1.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        User user = new User();
        user.setId(id);
        session.delete(user);
        tx1.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("From User");
        users = (List<User>) query.list();
        tx1.commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM User");
        query.executeUpdate();
        tx1.commit();
        session.close();
    }
}

package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

 /*   UserDaoJDBCImpl userDB;
    {
        try {
            userDB = new UserDaoJDBCImpl();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }*/
    UserDaoHibernateImpl userDB = new UserDaoHibernateImpl();

    public void createUsersTable() {
        userDB.createUsersTable();
        System.out.println(" Table Users created");

    }

    public void dropUsersTable() {
        userDB.dropUsersTable();
        System.out.println("Table Users droped");
    }

    public void saveUser(String name, String lastName, byte age) {
        userDB.saveUser(name, lastName, age);
        System.out.println("User saved ");
    }

    public void removeUserById(long id) {
        userDB.removeUserById(id);
        System.out.println("User with id = " + id +" is removed");
    }

    public List<User> getAllUsers() {
        return userDB.getAllUsers();
    }

    public void cleanUsersTable() {
        userDB.cleanUsersTable();
        System.out.println("Users table is cleaned");
    }
}

package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDaoJDBCImpl userDaoJDBC;
    {
        try {
            userDaoJDBC = new UserDaoJDBCImpl();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUsersTable() {
        userDaoJDBC.createUsersTable();
        System.out.println(" Table Users created");

    }

    public void dropUsersTable() {
        userDaoJDBC.dropUsersTable();
        System.out.println("Table Users droped");
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBC.saveUser(name, lastName, age);
        System.out.println("User saved ");
    }

    public void removeUserById(long id) {
        userDaoJDBC.removeUserById(id);
        System.out.println("User with id = " + id +" is removed");
    }

    public List<User> getAllUsers() {
        return userDaoJDBC.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoJDBC.cleanUsersTable();
        System.out.println("Users table is cleaned");
    }
}

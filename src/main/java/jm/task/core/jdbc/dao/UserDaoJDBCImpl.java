package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Statement stat = Util.getConnection();

    public UserDaoJDBCImpl() throws SQLException, ClassNotFoundException {

    }

    public void createUsersTable(){


        try {
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS User (Id INT PRIMARY KEY AUTO_INCREMENT, UserName VARCHAR(20), UserLastName VARCHAR(20), Age INT)");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try {
            stat.executeUpdate("drop table IF EXISTS User");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try{
            stat.executeUpdate("INSERT INTO User(UserName, UserLastName, Age) VALUES ("+"\""+name+"\""+","+"\""+lastName+"\""+","+"\""+age+"\""+")");

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try {
            stat.executeUpdate("DELETE FROM User WHERE Id = id");

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            ResultSet resultSet = stat.executeQuery("SELECT * FROM User");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                users.add(user);
            }
            return users;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        try {
            stat.executeUpdate("TRUNCATE TABLE User");

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }
}

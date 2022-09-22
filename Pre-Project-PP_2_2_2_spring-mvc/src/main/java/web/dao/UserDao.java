package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    List<User> getAllUsers();

    void updateUser(User user);

    User readUser(long id);

    User deleteUser(long id) throws NullPointerException;
}

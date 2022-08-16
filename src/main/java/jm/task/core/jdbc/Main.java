package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){


        UserServiceImpl user = new UserServiceImpl();
        //Statement stat = Util.getConnection();
        System.out.println("DB connected");
        List<User> users = user.getAllUsers();
        user.dropUsersTable();
        user.createUsersTable();
        System.out.println("Table Users created");
        Scanner in = new Scanner(System.in);

        while (users.size()<4){
            System.out.println("Enter User Name: ");
            String name = in.nextLine();
            System.out.println("Enter User Last Name: ");
            String lastname = in.nextLine();
            System.out.println("Enter User Age: ");
            byte age = in.nextByte();
            in.nextLine();
            user.saveUser(name, lastname, age);
            System.out.println("User: " + name + " " + lastname + " - added to table");
            users = user.getAllUsers();
        }

        System.out.println(users);
        user.cleanUsersTable();
        System.out.println("Table cleared");
        user.dropUsersTable();
        System.out.println("Table deleted");

    }

}



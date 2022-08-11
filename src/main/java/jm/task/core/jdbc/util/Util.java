package jm.task.core.jdbc.util;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

public class Util {
    public static Statement getConnection() throws SQLException, ClassNotFoundException {
        Dotenv dotenv = Dotenv.load();
        String username = dotenv.get("MY_ENV_NAME");
        String password = dotenv.get("MY_ENV_PASSWORD");
        String url = "jdbc:mysql://localhost:3306/task_1_1_3";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);

        return con.createStatement();

    }
}

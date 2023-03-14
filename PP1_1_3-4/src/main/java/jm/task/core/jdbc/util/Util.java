package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/kataDB";
    private static final String USER = "root";
    private static final String PASSWORD = "springcourse";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection connectionSetup() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
//            connection.setAutoCommit(false);
            System.out.println("Соединение установлено");
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Соединение не установлено");
            e.printStackTrace();
        }
        return connection;
    }
}

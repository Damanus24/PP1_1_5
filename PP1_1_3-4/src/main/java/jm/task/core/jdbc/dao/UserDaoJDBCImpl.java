package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    Connection connection = Util.connectionSetup();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {

            statement.execute("CREATE TABLE IF NOT EXISTS User (id BIGINT(19) NOT NULL AUTO_INCREMENT," +
                    " name VARCHAR(15), lastName VARCHAR(15), age TINYINT(3), PRIMARY KEY (id));");

            System.out.println("Таблица создана");
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException g) {
                g.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {

            statement.executeUpdate("DROP TABLE IF EXISTS User;");
            System.out.println("Таблица удалена");
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException g) {
                g.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = connection.createStatement()) {

            statement.executeUpdate("INSERT INTO User (name, lastName, age) " +
                    "VALUES ('" + name + "', '" + lastName + "', '" + age + "');");

            System.out.println("User с именем- " + name + " добавлен в базу данных");
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException g) {
                g.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement("DELETE FROM User WHERE id = '" + id + "';")) {

            System.out.println("Строка с id = " + id + " удалена");
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException g) {
                g.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM User");
            connection.commit();

            while (resultSet.next()) {
                User user = new User();
                user.setId((long) resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge((byte)resultSet.getInt("age"));
                userList.add(user);
            }

            System.out.println("Список сформирован");
            userList.forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException g) {
                g.printStackTrace();
            }
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {

            statement.executeUpdate("DELETE FROM User WHERE NOT id = 0");
            System.out.println("Очистка таблицы произведена");
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException g) {
                g.printStackTrace();
            }
        }
    }
}

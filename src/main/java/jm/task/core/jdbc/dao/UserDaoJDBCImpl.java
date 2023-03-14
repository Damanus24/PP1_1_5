package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Connection connection = Util.connectionSetup();
             PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS User " +
                     "(id BIGINT(19) NOT NULL AUTO_INCREMENT, name VARCHAR(15), lastName VARCHAR(15), " +
                     "age TINYINT(3), PRIMARY KEY (id));")) {

            preparedStatement.executeUpdate();
            System.out.println("Таблица создана");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//******РАБОЧИЙ МЕТОД С ОТКЛ. АВТОКОММИТ(если нужно переделать все методы так, напишите, пожалуйста.)*****************
//    @Override
//    public void createUsersTable() {
//        Connection connection = null;
//        try {
//            connection = Util.connectionSetup();
//            try (PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS User " +
//                    "(id BIGINT(19) NOT NULL AUTO_INCREMENT, name VARCHAR(15), lastName VARCHAR(15), " +
//                    "age TINYINT(3), PRIMARY KEY (id));")) {
//
//                preparedStatement.executeUpdate();
//                System.out.println("Таблица создана");
//                connection.commit();
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//                if (connection != null) {
//                    connection.rollback();
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//******РАБОЧИЙ МЕТОД С ОТКЛ. АВТОКОММИТ(если нужно переделать все методы так, напишите, пожалуйста.)*****************

    @Override
    public void dropUsersTable() {
        try (Connection connection = Util.connectionSetup();
             PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS User;")) {

            preparedStatement.executeUpdate();
            System.out.println("Таблица удалена");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.connectionSetup();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO User (name, lastName, age) " +
                     "VALUES ('" + name + "', '" + lastName + "', '" + age + "');")) {

            preparedStatement.executeUpdate();
            System.out.println("User с именем- " + name + " добавлен в базу данных");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Connection connection = Util.connectionSetup();
             PreparedStatement preparedStatement = connection
                .prepareStatement("DELETE FROM User WHERE id = '" + id + "';")) {

            preparedStatement.executeUpdate();
            System.out.println("Строка с id = " + id + " удалена");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Connection connection = Util.connectionSetup();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM User")) {

            ResultSet resultSet = preparedStatement.executeQuery();

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
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Connection connection = Util.connectionSetup();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM User WHERE NOT id = 0")) {

            preparedStatement.executeUpdate();
            System.out.println("Очистка таблицы произведена");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.sessionFactorySetup().getCurrentSession()) {

            session.beginTransaction();

            session.createSQLQuery("CREATE TABLE IF NOT EXISTS User " +
                    "(id BIGINT(19) NOT NULL AUTO_INCREMENT, name VARCHAR(15), lastName VARCHAR(15), " +
                    "age TINYINT(3), PRIMARY KEY (id));").executeUpdate();

            session.getTransaction().commit();
            System.out.println("Таблица создана");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.sessionFactorySetup().getCurrentSession()) {

            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS User;").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица удалена");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.sessionFactorySetup().getCurrentSession()) {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User с именем- " + name + " добавлен в базу данных");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.sessionFactorySetup().getCurrentSession()) {

            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
            System.out.println("Строка с id = " + id + " удалена");
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Session session = Util.sessionFactorySetup().getCurrentSession()) {

            session.beginTransaction();
            userList = session.createQuery("FROM User").getResultList();
            session.getTransaction().commit();
            System.out.println("Список сформирован");
            userList.forEach(System.out::println);
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.sessionFactorySetup().getCurrentSession()) {

            session.beginTransaction();
            session.createQuery("DELETE User WHERE NOT id = 0").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Очистка таблицы произведена");
        }
    }
}

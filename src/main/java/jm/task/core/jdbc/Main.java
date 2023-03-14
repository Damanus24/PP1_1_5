package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

//        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
//
//        userDaoJDBC.saveUser("Dauren", "Amangaliev", (byte)28);
//        userDaoJDBC.saveUser("Ivan", "Ivanov", (byte)22);
//        userDaoJDBC.saveUser("Petr", "Petrov", (byte)25);
//        userDaoJDBC.saveUser("Elena", "Sidorova", (byte)32);
//
//        userDaoJDBC.getAllUsers();
//        userDaoJDBC.cleanUsersTable();
//        userDaoJDBC.dropUsersTable();

        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

        userDaoHibernate.saveUser("Dauren", "Amangaliev", (byte)28);
        userDaoHibernate.saveUser("Ivan", "Ivanov", (byte)22);
        userDaoHibernate.saveUser("Petr", "Petrov", (byte)25);
        userDaoHibernate.saveUser("Elena", "Sidorova", (byte)32);

        userDaoHibernate.getAllUsers();
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();
    }
}

package ru.gb.lesson4.seminar.jpa;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JpaMain {

  public static void main(String[] args) throws SQLException {
//    try (Session session = HibernateUtil.getSession()) {
//      session.beginTransaction();
//      User user = new User();
//      user.setName("USER");
//      session.persist(user);
//      session.getTransaction().commit();
//    }
//
//    try (Session session = HibernateUtil.getSession()) {
//      session.beginTransaction();
//      List<User> selectUFromUser = session.createQuery("select u from User u", User.class).list();
//      System.out.println(selectUFromUser);
//      session.getTransaction().commit();
//    }

//    Connection connection = DriverManager.getConnection("jdbc:h2:mem:database");
//    createTables(connection);
//    insertData(connection);
//
//    try (Statement statement = connection.createStatement()) {
//      ResultSet resultSet = statement.executeQuery("select id, name from user");
//      while (resultSet.next()) {
//        int id = resultSet.getInt("id");
//        String name = resultSet.getString("name");
//        System.out.println("id = " + id + ", name = " + name);
//      }
//    }

  }

  private static void createTables(Connection connection) throws SQLException {
    try (Statement statement = connection.createStatement()) {
      statement.execute("create table if not exists user(id bigint, name varchar(256))");
    }
  }

  private static void insertData(Connection connection) throws SQLException {
    try (Statement statement = connection.createStatement()) {
      statement.execute("insert into user(id, name) values(1, 'Igor')");
      statement.execute("insert into user(id, name) values(2, 'Igor')");
    }
  }

}

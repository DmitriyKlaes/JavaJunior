package ru.gb.lesson4.seminar;

import java.sql.*;

public class Jdbc {

  public static void main(String[] args) throws SQLException {
    // RDBMS (SQL) Structure Query Language
    // postgres, oracle, mysql, h2, sqllite, ...
    // in-memory h2

    // JDBC Java DataBase Connectivity - набора классов в стандартной библиотеке,
    // которая предназначена для работы с реляционными БД


    // java.sql.Driver
    Connection connection = DriverManager.getConnection("jdbc:h2:mem:database.db");
    prepareTables(connection);
    insertData(connection);
    executeUpdate(connection);
    executeDelete(connection);
//    executeSelect(connection);

    try (PreparedStatement preparedStatement = connection.prepareStatement("select id, name from users where name = ?")) {
      preparedStatement.setString(1, "Igor");

      ResultSet resultSet = preparedStatement.executeQuery();
      int counter = 0;
      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");

        System.out.println("[" + counter++ + "] id = " + id + ", name = " + name);
      }
    }


    connection.close();
  }

  private static void executeSelect(Connection connection) throws SQLException {
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("select id, name from users where id = 2");
      int counter = 0;
      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");

        System.out.println("[" + counter++ + "] id = " + id + ", name = " + name);
      }
    }
  }

  private static void prepareTables(Connection connection) throws SQLException {
    try (Statement statement = connection.createStatement()) {
      statement.execute("""
        create table users (
          id bigint,
          name varchar(255)
        )
        """);
    }
  }

  private static void insertData(Connection connection) throws SQLException {
    try (Statement statement = connection.createStatement()) {
      int updatedRows = statement.executeUpdate("""
        insert into users(id, name) 
        values(1, 'Igor'),
              (2, 'User #2'),
              (3, 'User #3'),
              (4, 'User #4')""");
      System.out.println(updatedRows);

//      System.out.println(statement.executeUpdate("insert into users(id, name) values(1, 'Igor')"));
//      System.out.println(statement.executeUpdate("insert into users(id, name) values(2, 'User #2')"));
//      System.out.println(statement.executeUpdate("insert into users(id, name) values(3, 'User #3')"));
//      System.out.println(statement.executeUpdate("insert into users(id, name) values(4, 'User #4')"));
    }
  }

  private static void executeUpdate(Connection connection) throws SQLException {
    try (Statement statement = connection.createStatement()) {
      int updatedRows = statement.executeUpdate("update users set name = 'unknown' where id > 2");
      System.out.println(updatedRows);
    }
  }

  private static void executeDelete(Connection connection) throws SQLException {
    try (Statement statement = connection.createStatement()) {
      int deletedRows = statement.executeUpdate("delete from users where id = 4");
      System.out.println(deletedRows);
    }
  }

}

package ru.gb.lesson4.homework.task1;

import java.sql.*;
import java.util.Properties;

public class Main {

    /**
     * 1. С помощью JDBC выполнить:
     * 1.1 Создать таблицу book с колонками id bigint, name varchar, author varchar, ...
     * 1.2 Добавить в таблицу 10 книг
     * 1.3 Сделать запрос select from book where author = 'какое-то имя' и прочитать его с помощью ResultSet
     */

    public static void main(String[] args) throws SQLException {

        String url = "jdbc:postgresql://localhost:5432/";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "12345");

        Connection connection = DriverManager.getConnection(url, props);

        try (Statement statement = connection.createStatement()) {
            statement.execute("""
                    create table if not exists booktask1 (
                      id bigint PRIMARY KEY,
                      name varchar(255),
                      author varchar(255)
                    )
                    """);
        }

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("""
                    insert into booktask1(id, name, author)
                    values(1, 'Десять негритят', 'Агата Кристи'),
                          (2, 'Снеговик', 'Ю Нёсбе'),
                          (3, 'Унесенные ветром', 'Маргарет Митчел'),
                          (4, 'Война и мир', 'Лев Толстой'),
                          (5, 'Черный обелиск', 'Эрих Мария Ремарк'),
                          (6, 'Чистый код', 'Роберт Мартин'),
                          (7, 'На игле', 'Ирвин Уэлш'),
                          (8, 'Воскресение', 'Лев Толстой'),
                          (9, 'Страх и ненавить в лас вегасе', 'Хантер Эс Томас'),
                          (10, 'Ночное кино', 'Мариша Пэссэл')
                    """);
        }

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from booktask1 where author = 'Лев Толстой'");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");

                System.out.println("[" + id + "] name: " + name + ", author: " + author);
            }
        }

    }
}








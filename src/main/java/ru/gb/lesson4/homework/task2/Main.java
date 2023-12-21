package ru.gb.lesson4.homework.task2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.List;
import java.util.stream.LongStream;

public class Main {

    /**
     * 2. С помощью JPA(Hibernate) выполнить:
     * 2.1 Описать сущность Book из пункта 1.1
     * 2.2 Создать Session и сохранить в таблицу 10 книг
     * 2.3 Выгрузить список книг какого-то автора
     */

    public static void main(String[] args) throws SQLException {

        final SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            List<Book> books = LongStream.rangeClosed(1, 10)
                    .mapToObj(it -> new Book(it, "Book#" + it, "Author " + it))
                    .peek(session::persist)
                    .toList();

            // Для чистки таблиц:
//            List<Book> book = session.createQuery("select u from Book u order by id desc", Book.class)
//                    .getResultList();
//            book.forEach(session::remove);
//            session.flush();

            session.getTransaction().commit();
        }

        try (Session session = sessionFactory.openSession()) {
            List<Book> books = session.createQuery("select u from Book u where author = 'Author 1' order by id desc", Book.class)
                    .getResultList();

            System.out.println(books);
        }

        sessionFactory.close();

    }
}








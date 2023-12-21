package ru.gb.lesson4.seminar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Jpa {

  public static void main(String[] args) throws SQLException {
    // JPA Java Persistence API - набор правил (соглашений) по реализации доменной модели
    // Entity

    // Hibernate -> одна из реализаций стандарта JPA
    // EclipseLink

    final SessionFactory sessionFactory = new Configuration()
      .configure("hibernate.cfg.xml").buildSessionFactory();

    try (Session session = sessionFactory.openSession()) {
      session.beginTransaction();

      List<User> users = LongStream.rangeClosed(1, 10)
        .mapToObj(it -> new User(it))
        .peek(it -> it.setName("User #" + it.getId()))
        .peek(it -> session.persist(it))
        .collect(Collectors.toList());

      session.getTransaction().commit();
    }

    final User loadedUser;
    try (Session session = sessionFactory.openSession()) {
      session.beginTransaction();

      loadedUser = session.get(User.class, 1);
      loadedUser.setName("Updated");

//      session.merge(loadedUser);
      session.getTransaction().commit();
    }

    try (Session session = sessionFactory.openSession()) {
      // jql java query language
      // select id, name from users where id >= 1
      List<User> users = session.createQuery("select u from User u where id >= 1 order by id desc", User.class)
        .getResultList();

      System.out.println(users);
    }

    sessionFactory.close();
  }

}

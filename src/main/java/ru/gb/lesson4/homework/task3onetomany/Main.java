package ru.gb.lesson4.homework.task3onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.gb.lesson4.homework.EntityCreator;

import java.util.List;


public class Main {

    /*
     * 3.2 ** В классе Author создать поле List<Book>, которое описывает список всех книг этого автора. (OneToMany)
     */

    public static void main(String[] args) {

        final SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            List<AuthorOneToMany> authors = EntityCreator.createListAuthors();
            authors.forEach(session::persist);

            // Для чистки таблиц:
//            List<AuthorOneToMany> books = session.createQuery("select u from AuthorOneToMany u order by id desc", AuthorOneToMany.class)
//                    .getResultList();
//            books.forEach(session::remove);
//            session.flush();

            session.getTransaction().commit();
        }

        try (Session session = sessionFactory.openSession()) {
            List<AuthorOneToMany> books = session.createQuery("select u from AuthorOneToMany u order by id desc", AuthorOneToMany.class)
                    .getResultList();

            System.out.println(books);
        }

        sessionFactory.close();
    }
}

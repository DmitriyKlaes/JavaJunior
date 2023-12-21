package ru.gb.lesson4.homework.task3manytoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.gb.lesson4.homework.EntityCreator;

import java.util.List;


public class Main {

    /*
     * 3.* Создать сущность Автор (id biging, name varchar), и в сущности Book сделать поле типа Author (OneToOne)
     * 3.1 * Выгрузить Список книг и убедиться, что поле author заполнено
     */

    public static void main(String[] args) {

        final SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            List<BookManyToOne> book = EntityCreator.createListBooks();
            book.forEach(session::persist);

            // Для чистки таблиц:
//            List<BookManyToOne> books = session.createQuery("select u from BookManyToOne u order by id desc", BookManyToOne.class)
//                    .getResultList();
//            books.forEach(session::remove);
//            session.flush();

            session.getTransaction().commit();
        }

        try (Session session = sessionFactory.openSession()) {
            List<BookManyToOne> books = session.createQuery("select u from BookManyToOne u order by id desc", BookManyToOne.class)
                    .getResultList();

            System.out.println(books);
        }

        sessionFactory.close();
    }
}

package ru.gb.lesson4.seminar.jpa;

//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;

public class HibernateUtil {
//  private static final SessionFactory sessionFactory;

  static {
    try {
      // Create the SessionFactory from hibernate.cfg.xml
//      sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    } catch (Throwable ex) {
      System.err.println("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }

//  public static Session getSession() {
//    return sessionFactory.openSession();
//  }

  // Additional methods for CRUD operations
}


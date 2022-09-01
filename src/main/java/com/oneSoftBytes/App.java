package com.oneSoftBytes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //create configuration
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Song.class);

        //create session factory
        //singleton and immutable should create once
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        //Initialize the session object
        Session session = sessionFactory.openSession();

        Song song1 = new Song();

        song1.setId(1);
        song1.setSongName("Believer");
        song1.setArtist("Jack Denial");

        session.beginTransaction();
        session.save(song1);
        session.getTransaction().commit();

        System.out.println("saved...check db....");

        session.close();

        //new session object
        Session session1 = sessionFactory.openSession();
        session1.beginTransaction();
        session1.save(new Song(2, "ABC", "Singer 1"));
        session1.getTransaction().commit();
        session1.close();
    }
}

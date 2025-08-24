package com.onetoone;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        session.beginTransaction();

        Profile profile = new Profile();
        profile.setEmail("user@example.com");
        profile.setPhone("1234567890");

        User user = new User();
        user.setUsername("john_doe");
        user.setProfile(profile);

        session.save(user); // cascade saves profile

        session.getTransaction().commit();
        session.close();
        factory.close();

    }

}

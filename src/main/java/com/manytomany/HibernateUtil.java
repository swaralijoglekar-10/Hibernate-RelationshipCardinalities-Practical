package com.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();

        Session session = factory.openSession();
        session.beginTransaction();

        Student s1 = new Student("Alice");
       // s1.setId(1);
        Student s2 = new Student("Bob");
       // s2.setId(2);

        Course c1 = new Course("Math");
       // c1.setId(1);
        Course c2 = new Course("Physics");
      //  c2.setId(2);

        // Assign courses to students
        s1.getCourses().add(c1);
        s1.getCourses().add(c2);
        s2.getCourses().add(c1);

        // Save to DB
        session.merge(c1);  // first
        session.merge(s1);
        session.merge(s2);
        session.merge(c2);


        session.getTransaction().commit();
        session.close();
        factory.close();

        System.out.println("Records saved successfully.");
    }

}
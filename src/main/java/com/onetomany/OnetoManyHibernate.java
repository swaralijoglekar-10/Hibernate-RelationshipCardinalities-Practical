package com.onetomany;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
public class OnetoManyHibernate {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();

        Session session = factory.openSession();
        session.beginTransaction();

        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setAge(10);;
        customer.setId(100);

        Order order1 = new Order();
        order1.setProduct("Laptop");
        order1.setCustomer(customer);

        Order order2 = new Order();
        order2.setProduct("Smartphone");
        order2.setCustomer(customer);

        customer.setOrders(Arrays.asList(order1, order2));

        session.save(customer); // Automatically saves orders due to cascade

        session.getTransaction().commit();
        session.close();
        factory.close();

    }

}
package com.multipleNamedQueries;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtilPurchasers {
    public static void main(String[] args)
    {
	 /* SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	  Session session = sessionFactory.openSession();*/
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").addAnnotatedClass(Purchaser.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Long> recCount= session.createNamedQuery("GET_PURCHASERS_COUNT").getResultList();

        List<Purchaser> custObj=session.createNamedQuery("GET_ALL_PURCHASERS").getResultList();

        for(Long custCnt : recCount)
        {
            System.out.println("Record count   "+custCnt);
        }


        for(Purchaser cust : custObj)
        {
            System.out.println("purchaser details   "+cust.getAge()+"======="+cust.getId()+"===="+cust.getName());
        }
        session.getTransaction().commit();
        session.close();
    }

}


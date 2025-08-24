package com.example.app;

import com.example.entity.AccountEntity;
import com.example.entity.EmployeeEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Build SessionFactory from hibernate.cfg.xml
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // 1. Create Employee
            EmployeeEntity employee1 = new EmployeeEntity("john.doe@example.com", "John", "Doe");

            // 2. Create Accounts for employee1
            AccountEntity account1 = new AccountEntity("ACC-001-JD");
            AccountEntity account2 = new AccountEntity("ACC-002-JD");

            // 3. Add accounts to the employee
            employee1.addAccount(account1);
            employee1.addAccount(account2);

            // 4. Save the employee (accounts will be cascaded)
            System.out.println("Saving employee and their accounts...");
            session.persist(employee1); // Use persist for new entities
            System.out.println("Employee saved: " + employee1);
            System.out.println("Accounts for employee: " + employee1.getAccounts());


            // Create another employee with one account
            EmployeeEntity employee2 = new EmployeeEntity("jane.smith@example.com", "Jane", "Smith");
            AccountEntity account3 = new AccountEntity("ACC-003-JS");
            employee2.addAccount(account3);
            System.out.println("\nSaving another employee and their account...");
            session.persist(employee2);
            System.out.println("Employee saved: " + employee2);
            System.out.println("Accounts for employee: " + employee2.getAccounts());


            // Commit the transaction
            transaction.commit();
            System.out.println("\nTransaction committed successfully!");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        // --- Demonstrate Retrieval ---
        Session session2 = null;
        try {
            session2 = sessionFactory.openSession();
            // Retrieve an employee and its associated accounts
            System.out.println("\n--- Retrieving Data ---");
            EmployeeEntity retrievedEmployee = session2.get(EmployeeEntity.class, 1); // Get employee with ID 1
            if (retrievedEmployee != null) {
                System.out.println("Retrieved Employee: " + retrievedEmployee.getFirstName() + " " + retrievedEmployee.getLastName());
                System.out.println("Associated Accounts:");
                for (AccountEntity account : retrievedEmployee.getAccounts()) {
                    System.out.println("  - " + account.getAccNumber() + " (ID: " + account.getAccountId() + ")");
                }
            } else {
                System.out.println("Employee with ID 1 not found.");
            }

            System.out.println("\n--- Listing All Employees ---");
            List<EmployeeEntity> allEmployees = session2.createQuery("FROM EmployeeEntity", EmployeeEntity.class).list();
            for (EmployeeEntity emp : allEmployees) {
                System.out.println(emp.getFirstName() + " " + emp.getLastName() + " has " + emp.getAccounts().size() + " account(s).");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session2 != null) {
                session2.close();
            }
            if (sessionFactory != null) {
                sessionFactory.close(); // Close the SessionFactory at the end of the application
            }
        }
    }
}

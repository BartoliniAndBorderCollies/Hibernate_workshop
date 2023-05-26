package org.example;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee("Jan", LocalDate.of(1999, Month.MARCH,2), "jan@wp.pl");
        Employee employee2 = new Employee("Misia", LocalDate.of(1995, 3,2), "misia@wp.pl");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        EntityManager entityManager = sessionFactory.createEntityManager();

        // INSERT
        entityManager.getTransaction().begin();
        entityManager.persist(employee2);
        entityManager.persist(employee);
        entityManager.getTransaction().commit();

        //FIND - jak robie find to nie musze robic begin transaction

        Employee foundEmployee = entityManager.find(Employee.class, 1);
        System.out.println("Pracownik o id = 1:");
        System.out.println(foundEmployee.toString());

        //MERGE czyli update
        foundEmployee.setEmail("buziaczek@gmail.com");
        foundEmployee.setName("Lala");

        entityManager.getTransaction().begin();
        entityManager.merge(foundEmployee);
        entityManager.getTransaction().commit();

        System.out.println(entityManager.find(Employee.class, foundEmployee.getId()));

        //REMOVE
//        entityManager.getTransaction().begin();
//        entityManager.remove(foundEmployee);
//        entityManager.getTransaction().commit();
//
        //poniżej uzywamy HQL do uzyskania listy rekordów z tabeli

        List<Employee> employeeList = entityManager.createQuery("from Employee", Employee.class).getResultList();
        System.out.println(employeeList);


        HibernateUtil.shutdown();
    }
}
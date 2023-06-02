package org.example;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Jeden dział może mieć wielu pracowników One to Many
        // Ale każdy pracownik może należeć do tylko jednego działu Many to One

        Department departmentIt = new Department("it");
        Employee employee = new Employee("Jan", LocalDate.of(1999, Month.MARCH, 2), "jan@wp.pl");
        Employee employee2 = new Employee("Misia", LocalDate.of(1995, 3, 2), "misia@wp.pl");
        Employee employee3 = new Employee("Missdia", LocalDate.of(1995, 3, 2), "misia@wp.pl");
        Employee employee4 = new Employee("Midfgsia", LocalDate.of(1995, 3, 2), "misia@wp.pl");
        Employee employee5 = new Employee("Misgfhia", LocalDate.of(1995, 3, 2), "misia@wp.pl");

        //do employee przypisuję departament
        employee.setDepartment(departmentIt);
        employee2.setDepartment(departmentIt);
        employee3.setDepartment(departmentIt);
        employee4.setDepartment(departmentIt);
        employee5.setDepartment(departmentIt);

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        EntityManager entityManager = sessionFactory.createEntityManager();

        // PERSIST
        entityManager.getTransaction().begin();
        entityManager.persist(departmentIt);
        entityManager.persist(employee2);
        entityManager.persist(employee);
        entityManager.persist(employee3);
        entityManager.persist(employee4);
        entityManager.persist(employee5);
        entityManager.getTransaction().commit();

        //FIND - jak robie find to nie musze robic begin transaction

        EntityManager em2 = sessionFactory.createEntityManager(); // po to żeby cache wyczyscic
//        entityManager.flush(); // lub po to żeby cache wyczyscic (bo hibernate trzyma cache)

        int id = 2;

        Employee foundEmployee = em2.find(Employee.class, id);
        System.out.println("Pracownik o id = " + id + ":");
        System.out.println(foundEmployee.toString());

        Department foundDepartment = em2.find(Department.class, 1);

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
package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

// 1. dodaj w pliku pom.xml po dwóch enterach kod z dependencies
// 2. stwórz w resources plik hibernate.cfg.xml i skopiuj tam kod do hibernate
// 3. w workbenchu stwórz schema i usera oraz nadaj mu hasła i priviliges - dane te zapisz w hibernate.cfg.xml
// pamietaj zeby w pliku cfg dodac nazwe bazy danych oraz na samym dole mapping -> klasy odpowiednia tam gdzie encje
// 4. stwórz klasę HibernateUtil i skopiuj poniższy kod na tworzenie SessionFactory i zamykanie jej.

// 5. //Metoda do Main na sprawdzenie łącznosci:

//public static void main(String[] args) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//
//        // Check database version (because there is nothing else in database)
//        String sql = "select version()";
//
//        String result = (String) session.createNativeQuery(sql).getSingleResult();
//        System.out.println(result);
// session.close();

//
//        HibernateUtil.shutdown();
//        }

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static SessionFactory buildSessionFactory() {
        try {
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml").build();

            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

            return metadata.getSessionFactoryBuilder().build();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

}

package br.com.alexpfx.supermarket.crawler.model.database.hibernate.util;

import br.com.alexpfx.supermarket.crawler.model.domain.Manufacturer;
import br.com.alexpfx.supermarket.crawler.model.domain.Product;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by alexandre on 22/12/2015.
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configure = new Configuration().configure();


            configure.addAnnotatedClass(Product.class);
            configure.addAnnotatedClass(Manufacturer.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configure.getProperties()).build();
            return configure.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
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

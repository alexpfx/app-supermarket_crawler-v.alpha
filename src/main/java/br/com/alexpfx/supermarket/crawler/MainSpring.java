package br.com.alexpfx.supermarket.crawler;

import br.com.alexpfx.supermarket.crawler.model.domain.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by alexandre on 26/12/2015.
 */
public class MainSpring {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/config/bean-locations.xml");

        SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");

        Session session = sessionFactory.openSession();
        Product p = new Product();
        p.setDescription("lfsafa");
        p.setUrl("fdasfaf");
        session.save(p);
        session.flush();
        session.close();

    }


}

package br.com.alexpfx.supermarket.crawler.model.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

/**
 * Created by alexandre on 27/12/2015.
 */
public class CustomHibernateDaoSupport extends HibernateDaoSupport {



    @Autowired
    public void anyMethodName (SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }

}

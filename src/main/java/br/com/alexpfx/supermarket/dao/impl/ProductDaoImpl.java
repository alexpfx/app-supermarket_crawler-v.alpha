package br.com.alexpfx.supermarket.dao.impl;

import br.com.alexpfx.supermarket.dao.ProductDao;
import br.com.alexpfx.supermarket.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by alexandre on 27/12/2015.
 */
@Component
public class ProductDaoImpl implements ProductDao<Product> {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Product product) {
        entityManager.persist(product);
    }

    @Override
    public void update(Product product) {
        entityManager.merge(product);

    }

    @Override
    public void delete(Product product) {


    }



    @Override
    public Product findByPk(int pk) {
        return null;
    }

    @Override
    public List<Product> find(Product product) {
        return null;
    }
}

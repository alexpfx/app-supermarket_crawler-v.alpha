package br.com.alexpfx.supermarket.crawler.model.dao.impl;

import br.com.alexpfx.supermarket.crawler.model.dao.CustomHibernateDaoSupport;
import br.com.alexpfx.supermarket.crawler.model.dao.ProductDao;
import br.com.alexpfx.supermarket.crawler.model.domain.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

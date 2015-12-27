package br.com.alexpfx.supermarket.crawler.model.bo.impl;

import br.com.alexpfx.supermarket.crawler.model.bo.ProductBo;
import br.com.alexpfx.supermarket.crawler.model.dao.ProductDao;
import br.com.alexpfx.supermarket.crawler.model.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by alexandre on 27/12/2015.
 */
@Component
public class ProductBoImpl implements ProductBo {

    @Autowired
    ProductDao<Product> dao;

    @Override
    @Transactional
    public void save(Product p) {
        dao.save(p);
    }

    @Override
    @Transactional
    public void update(Product p) {
        dao.update(p);
    }

    @Override
    @Transactional
    public void delete(Product p) {
        dao.delete(p);

    }

    @Override
    @Transactional(readOnly = true)
    public Product find(String code) {
        return dao.findByPk(Integer.parseInt(code));
    }
}

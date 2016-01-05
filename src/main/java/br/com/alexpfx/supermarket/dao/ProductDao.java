package br.com.alexpfx.supermarket.dao;

import java.util.List;

/**
 * Created by alexandre on 27/12/2015.
 */
public interface ProductDao<Product> {


    void save(br.com.alexpfx.supermarket.domain.Product product);

    void update(br.com.alexpfx.supermarket.domain.Product product);

    void delete(br.com.alexpfx.supermarket.domain.Product product);

    br.com.alexpfx.supermarket.domain.Product findByPk(int pk);

    List<br.com.alexpfx.supermarket.domain.Product> find(br.com.alexpfx.supermarket.domain.Product product);
}

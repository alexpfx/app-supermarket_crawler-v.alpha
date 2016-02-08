package br.com.alexpfx.supermarket.dao;

import br.com.alexpfx.supermarket.domain.Product;

import java.util.List;

/**
 * Created by alexandre on 27/12/2015.
 */
public interface BaseDao<T> {

    void save(T t);

    void update(T t);

    void delete(T t);

    Product findByPk(int pk);

    List<Product> find(T t);

    List<Product> findAll ();

}

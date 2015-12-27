package br.com.alexpfx.supermarket.crawler.model.dao;

import java.util.List;

/**
 * Created by alexandre on 27/12/2015.
 */
public interface Dao<T> {
    void save(T t);

    void update(T t);

    void delete(T t);

    T findByPk(int pk);

    List<T> find(T t);

}

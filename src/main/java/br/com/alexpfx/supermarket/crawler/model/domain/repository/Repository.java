package br.com.alexpfx.supermarket.crawler.model.domain.repository;

import br.com.alexpfx.supermarket.crawler.model.database.Dao;

/**
 * Created by alexandre on 16/12/2015.
 */
public interface Repository <T> {

    void setDao (Dao<T> dao);

}

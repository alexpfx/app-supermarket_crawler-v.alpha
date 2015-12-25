package br.com.alexpfx.supermarket.crawler.model.database;

import br.com.alexpfx.supermarket.crawler.model.domain.Product;

import java.sql.SQLException;

/**
 * Created by alexandre on 12/12/2015.
 */
public interface ProductDao extends Dao{

    void save (Product product) ;
    boolean exists (Product product);
    void delete (Product product);

}

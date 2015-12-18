package br.com.alexpfx.supermarket.crawler.model.domain.repository;

import br.com.alexpfx.supermarket.crawler.model.domain.Product;
import com.sun.org.apache.regexp.internal.RE;

import java.util.Collection;

/**
 * Created by alexandre on 16/12/2015.
 */
public interface ProductRepository extends Repository<Product> {

    void add(Product p);

    Iterable<Product> find(Product p);

    void remove(Product p);


}

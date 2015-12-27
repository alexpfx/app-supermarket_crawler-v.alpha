package br.com.alexpfx.supermarket.crawler.model.bo;

import br.com.alexpfx.supermarket.crawler.model.domain.Product;

/**
 * Created by alexandre on 27/12/2015.
 */
public interface ProductBo {
    void save (Product p);
    void update (Product p);
    void delete (Product p);
    Product find (String code);
}

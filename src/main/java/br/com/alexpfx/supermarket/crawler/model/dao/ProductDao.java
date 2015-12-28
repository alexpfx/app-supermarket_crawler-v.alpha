package br.com.alexpfx.supermarket.crawler.model.dao;

import br.com.alexpfx.supermarket.crawler.model.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by alexandre on 27/12/2015.
 */
public interface ProductDao <Product> {


    void save(br.com.alexpfx.supermarket.crawler.model.domain.Product product);

    void update(br.com.alexpfx.supermarket.crawler.model.domain.Product product);

    void delete(br.com.alexpfx.supermarket.crawler.model.domain.Product product);

    br.com.alexpfx.supermarket.crawler.model.domain.Product findByPk(int pk);

    List<br.com.alexpfx.supermarket.crawler.model.domain.Product> find(br.com.alexpfx.supermarket.crawler.model.domain.Product product);
}

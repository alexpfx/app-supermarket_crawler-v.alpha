package br.com.alexpfx.supermarket.crawler.model.domain.repository;

import br.com.alexpfx.supermarket.crawler.model.database.Dao;
import br.com.alexpfx.supermarket.crawler.model.database.ProductDao;
import br.com.alexpfx.supermarket.crawler.model.domain.Product;
import com.google.common.base.Preconditions;
import org.apache.http.util.Asserts;

import java.util.Collection;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by alexandre on 16/12/2015.
 */
public class ProductRepositoryImpl implements ProductRepository {

    private ProductDao productDao;


    @Override
    public void add(Product p) {
        checkNotNull(p);
    }

    @Override
    public Collection<Product> find(Product p) {
        checkNotNull(p);

        return null;
    }

    @Override
    public void remove(Product p) {

    }


}

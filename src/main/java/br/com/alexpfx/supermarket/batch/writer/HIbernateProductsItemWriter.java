package br.com.alexpfx.supermarket.batch.writer;

import br.com.alexpfx.supermarket.dao.ProductDao;
import br.com.alexpfx.supermarket.domain.Product;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by alexandre on 10/01/2016.
 */
public class HIbernateProductsItemWriter implements ItemWriter<Product> {

    @Autowired
    private ProductDao productDao;

    @Override
    public void write(List<? extends Product> list) throws Exception {
        list.forEach(p -> productDao.save(p));
    }
}

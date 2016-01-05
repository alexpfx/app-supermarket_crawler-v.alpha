package br.com.alexpfx.supermarket.crawler.batch;

import br.com.alexpfx.supermarket.crawler.batch.reader.ProductList;
import br.com.alexpfx.supermarket.crawler.jaunt.ProductExtractedListener;
import br.com.alexpfx.supermarket.crawler.model.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by alexandre on 04/01/2016.
 */
public class RibeiraoListener implements ProductExtractedListener {

    @Autowired
    ProductList productList;

    @Override
    public void productExtracted(Product product) {
        productList.add(product);
    }
}

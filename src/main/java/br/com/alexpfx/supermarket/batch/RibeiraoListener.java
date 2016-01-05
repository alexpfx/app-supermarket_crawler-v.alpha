package br.com.alexpfx.supermarket.batch;

import br.com.alexpfx.supermarket.batch.reader.ProductList;
import br.com.alexpfx.supermarket.crawler.ProductExtractedListener;
import br.com.alexpfx.supermarket.domain.Product;
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

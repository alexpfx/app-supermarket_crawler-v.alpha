package br.com.alexpfx.supermarket.crawler.jaunt;

import br.com.alexpfx.supermarket.crawler.model.domain.Product;

/**
 * Created by alexandre on 01/01/2016.
 */
public interface ProductExtractedListener {
    void productExtracted(Product product);
}

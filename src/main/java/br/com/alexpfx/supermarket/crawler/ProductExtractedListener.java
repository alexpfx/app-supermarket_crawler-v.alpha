package br.com.alexpfx.supermarket.crawler;

import br.com.alexpfx.supermarket.domain.Product;

/**
 * Created by alexandre on 01/01/2016.
 */
public interface ProductExtractedListener {
    void productExtracted(Product product);
}

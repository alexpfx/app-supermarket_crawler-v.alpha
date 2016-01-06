package br.com.alexpfx.supermarket.webcrawler.listeners;

import br.com.alexpfx.supermarket.domain.Product;

/**
 * Created by alexandre on 01/01/2016.
 */
public interface ProductExtractedListener extends CrawlerListener{
    void productExtracted(Product product);
}

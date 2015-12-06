package br.com.alexpfx.supermarket.crawler.controller;

import br.com.alexpfx.supermarket.crawler.model.ProductInfo;

/**
 * Created by alexandre on 04/12/2015.
 */
public interface CrawlerListener {

    void onProductVisit(ProductInfo productInfo);
}

package br.com.alexpfx.supermarket.crawler.controller.angeloni;

import br.com.alexpfx.supermarket.crawler.controller.Crawler;
import br.com.alexpfx.supermarket.crawler.controller.CrawlerListener;
import br.com.alexpfx.supermarket.crawler.controller.CrawlerModel;
import br.com.alexpfx.supermarket.crawler.model.ProductInfo;

/**
 * Created by alexandre on 06/12/2015.
 */
public class AngeloniCrawler extends Crawler {

    public AngeloniCrawler() {
        super(new AngeloniCrawlerModel(), new CrawlerListener() {
            public void onProductVisit(ProductInfo productInfo) {
                System.out.println("visit");
            }
        });
    }
}

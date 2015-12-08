package br.com.alexpfx.supermarket.crawler.controller.angeloni;

import br.com.alexpfx.supermarket.crawler.controller.Crawler;
import br.com.alexpfx.supermarket.crawler.controller.CrawlerListener;
import br.com.alexpfx.supermarket.crawler.model.ProductInfo;
import edu.uci.ics.crawler4j.crawler.CrawlController;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by alexandre on 06/12/2015.
 */
public class AngeloniCrawler extends Crawler {

    private CopyOnWriteArrayList<ProductInfo> produtos = new CopyOnWriteArrayList<ProductInfo>();


    @Override
    public void init() {
        setListener(new CrawlerListener() {
            public void onProductVisit(ProductInfo productInfo) {
                produtos.add(productInfo);
                System.out.println(productInfo);
            }
        });


        setCrawlerModel(new AngeloniCrawlerModel());

    }
}

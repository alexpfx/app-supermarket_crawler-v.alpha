package br.com.alexpfx.supermarket.crawler.controller.angeloni;

import br.com.alexpfx.supermarket.crawler.controller.Crawler;
import br.com.alexpfx.supermarket.crawler.controller.CrawlerListener;
import br.com.alexpfx.supermarket.crawler.model.Crud;
import br.com.alexpfx.supermarket.crawler.model.to.ProductInfoTO;
import com.firebase.client.Firebase;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by alexandre on 06/12/2015.
 */
public class AngeloniCrawler extends Crawler {

    public static final String REF_URL = "https://supermarketcrawler.firebaseio.com/";
    private CopyOnWriteArrayList<ProductInfoTO> produtos = new CopyOnWriteArrayList<ProductInfoTO>();

    private Crud <ProductInfoTO> productInfoCrud = new Crud<>(new Firebase(REF_URL));

    @Override
    public void init() {
        setListener(new CrawlerListener() {
            public void onProductVisit(ProductInfoTO productInfo) throws InterruptedException {
                productInfoCrud.save("produtos", productInfo.getId(), productInfo);
            }
        });
        setCrawlerModel(new AngeloniCrawlerModel());
    }
}

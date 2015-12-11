package br.com.alexpfx.supermarket.crawler.controller.angeloni;

import br.com.alexpfx.supermarket.crawler.controller.Crawler;
import br.com.alexpfx.supermarket.crawler.controller.CrawlerListener;
import br.com.alexpfx.supermarket.crawler.model.database.Crud;
import br.com.alexpfx.supermarket.crawler.model.domain.*;
import br.com.alexpfx.supermarket.crawler.model.to.ProductInfoTO;
import com.firebase.client.Firebase;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by alexandre on 06/12/2015.
 */
public class AngeloniCrawler extends Crawler {

    public static final String REF_URL = "https://supermarketcrawler.firebaseio.com/";
    private CopyOnWriteArrayList<ProductInfoTO> produtos = new CopyOnWriteArrayList<ProductInfoTO>();
    private Crud <Product> productInfoCrud = new Crud<>(new Firebase(REF_URL));
    private KeywordCounter counter = new KeywordCounter();


    @Override
    public void init() {
        setListener(new CrawlerListener() {
            public void onProductVisit(ProductInfoTO productInfo) throws InterruptedException {
                PriceHistory h = new PriceHistory();
                h.add(PriceDate.of(new Date(), productInfo.getPrice()));
                Product p = Product.of(Seller.of("Angeloni"), productInfo.getDescription(), h, Keywords.ofPhrase(productInfo.getDescription()));
                productInfoCrud.save("/angeloni/produtos/", p);
                counter.add(p.getKeywords());
                System.out.println(counter.top(40));
                System.out.println();
            }
        });
        setCrawlerModel(new AngeloniCrawlerModel());

    }
}

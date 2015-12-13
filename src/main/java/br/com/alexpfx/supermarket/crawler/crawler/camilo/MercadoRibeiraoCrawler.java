package br.com.alexpfx.supermarket.crawler.crawler.camilo;

import br.com.alexpfx.supermarket.crawler.crawler.Crawler;
import br.com.alexpfx.supermarket.crawler.crawler.CrawlerListener;
import br.com.alexpfx.supermarket.crawler.model.database.Crud;
import br.com.alexpfx.supermarket.crawler.model.domain.Product;
import br.com.alexpfx.supermarket.crawler.model.to.ProductInfoTO;
import com.firebase.client.Firebase;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexandre on 12/12/2015.
 */
public class MercadoRibeiraoCrawler extends Crawler {
    public static final String REF_URL = "https://smket.firebaseio.com/";
    private Crud<Map.Entry> productInfoCrud = new Crud<>(new Firebase(REF_URL));

    @Override
    public void init() {

        setListener(new CrawlerListener() {
            @Override
            public void onProductVisit(ProductInfoTO productInfo) throws InterruptedException {
                AbstractMap.SimpleImmutableEntry<String, String> valor = new AbstractMap.SimpleImmutableEntry<>("codigo", productInfo.getId());
                productInfoCrud.save("/produtos/"+productInfo.getDescription().toLowerCase(), valor);
            }
        });

        setCrawlerModel(new MercadoRibeiraoCrawlerModel());
    }
}

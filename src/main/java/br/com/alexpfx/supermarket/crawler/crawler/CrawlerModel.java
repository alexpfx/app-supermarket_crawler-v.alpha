package br.com.alexpfx.supermarket.crawler.crawler;

import br.com.alexpfx.supermarket.crawler.model.ExtractProductError;
import br.com.alexpfx.supermarket.crawler.model.to.ProductInfoTO;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * Created by alexandre on 04/12/2015.
 */
public interface CrawlerModel {

    boolean shouldVisit(Page page, WebURL webURL);

    ProductInfoTO extractProduct (Page page) throws ExtractProductError;


    boolean isProductPage(String url);
}

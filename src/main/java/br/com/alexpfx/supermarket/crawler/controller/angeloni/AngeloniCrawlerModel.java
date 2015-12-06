package br.com.alexpfx.supermarket.crawler.controller.angeloni;

import br.com.alexpfx.supermarket.crawler.controller.CrawlerModel;
import br.com.alexpfx.supermarket.crawler.model.ExtractProductError;
import br.com.alexpfx.supermarket.crawler.model.ProductInfo;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * Created by alexandre on 06/12/2015.
 */
public class AngeloniCrawlerModel implements CrawlerModel {
    public boolean isProductPage(Page page, WebURL webURL) {
        return false;
    }

    public ProductInfo extractProduct(Page page) throws ExtractProductError {
        return null;
    }
}

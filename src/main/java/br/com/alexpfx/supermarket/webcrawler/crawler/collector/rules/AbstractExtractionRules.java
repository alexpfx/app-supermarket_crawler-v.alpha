package br.com.alexpfx.supermarket.webcrawler.crawler.collector.rules;

import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.CrawlerAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.collector.ExtractionRules;

/**
 * Created by alexandre on 31/01/2016.
 */


public abstract class AbstractExtractionRules<T, D extends CrawlerAPI<?>> implements ExtractionRules<T> {
    private D crawlerAPI;

    public AbstractExtractionRules(D crawlerAPI) {
        this.crawlerAPI = crawlerAPI;
    }

    public D getCrawlerAPI() {
        return crawlerAPI;
    }
}

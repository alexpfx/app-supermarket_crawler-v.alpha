package br.com.alexpfx.supermarket.webcrawler.crawler.collector.rules;

import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.CrawlerAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.collector.ExtractionRules;

/**
 * Created by alexandre on 31/01/2016.
 */


public abstract class AbstractExtractionRules<T, D extends CrawlerAPI<?>> implements ExtractionRules<T> {

    private D parserAPI;

    public AbstractExtractionRules(D parserAPI) {
        this.parserAPI = parserAPI;
    }

    public D getCrawlerAPI() {
        return parserAPI;
    }
}

package br.com.alexpfx.supermarket.webcrawler.crawler.collector;

import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.CrawlerAPI;

import java.util.List;

/**
 * This class was created to avoid subclass to have to implement setters for collectorRule,collectorListener and userAgent and to
 * ensure userAgent is not null.
 */
public abstract class AbstractCollector<T> implements Collector<T> {

    public AbstractCollector(CrawlerAPI crawlerAPI) {
        this.crawlerAPI = crawlerAPI;
    }

    // fields are to be used ("exclusively") in subclasses, so I use protected modifier.
    protected CollectorRule<T> collectorRule = (CollectorRule<T>) CollectorRule.EMPTY;

    protected CrawlerAPI crawlerAPI;


    @Override
    public final List<T> collect(List<String> urls) {
        return doCollect(urls);
    }

    protected abstract List<T> doCollect(List<String> urls);
}

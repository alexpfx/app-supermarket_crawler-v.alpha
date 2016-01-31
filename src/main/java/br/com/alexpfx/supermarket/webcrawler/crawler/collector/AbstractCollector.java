package br.com.alexpfx.supermarket.webcrawler.crawler.collector;

import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.CrawlerAPI;

import java.util.List;

public abstract class AbstractCollector<T, A extends CrawlerAPI<?>> implements Collector<T> {

    private A crawlerAPI;

    public AbstractCollector(A crawlerAPI) {
        this.crawlerAPI = crawlerAPI;
    }

    protected ExtractionRules<T> extractionRules = (ExtractionRules<T>) ExtractionRules.EMPTY;

    public A getCrawlerAPI() {
        return crawlerAPI;
    }

    @Override
    public final List<T> collect(List<String> urls) {
        return doCollect(urls);
    }

    protected abstract List<T> doCollect(List<String> urls);
}

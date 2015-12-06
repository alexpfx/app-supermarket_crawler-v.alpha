package br.com.alexpfx.supermarket.crawler.controller;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * Created by alexandre on 04/12/2015.
 */
public abstract class Crawler extends WebCrawler {

    private CrawlerModel crawlerModel;
    private CrawlerListener listener;

    protected Crawler(CrawlerModel crawlerModel, CrawlerListener listener) {
        this.crawlerModel = crawlerModel;
        this.listener = listener;
    }

    @Override
    public boolean shouldVisit(Page page, WebURL url) {
        return crawlerModel.isProductPage(page, url);
    }

    @Override
    public void visit(Page page) {
        super.visit(page);
    }
}

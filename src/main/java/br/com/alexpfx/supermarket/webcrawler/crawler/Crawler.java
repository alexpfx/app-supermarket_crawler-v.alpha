package br.com.alexpfx.supermarket.webcrawler.crawler;

import br.com.alexpfx.supermarket.webcrawler.listeners.CrawlerListener;

/**
 * Created by alexandre on 27/12/2015.
 */
public interface Crawler {
    void crawl();

    void setListener(CrawlerListener listener);
}

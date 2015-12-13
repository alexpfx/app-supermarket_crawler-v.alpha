package br.com.alexpfx.supermarket.crawler.crawler;

/**
 * Created by alexandre on 06/12/2015.
 */
public interface CrawlerControllerConfig {
    String getStorageFolder();

    int getNumberOfCrawlers();

    String[] getSeeds();

    Class<? extends Crawler> getControllerClass();
}

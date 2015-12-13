package br.com.alexpfx.supermarket.crawler.crawler.meener;

import br.com.alexpfx.supermarket.crawler.crawler.CrawlerControllerConfig;
import br.com.alexpfx.supermarket.crawler.crawler.Crawler;

/**
 * Created by alexandre on 12/12/2015.
 */
public class MeenerCrawlerControllerConfig implements CrawlerControllerConfig {

    @Override
    public String getStorageFolder() {
        return "data/meener/root";
    }

    @Override
    public int getNumberOfCrawlers() {
        return 1;
    }

    @Override
    public String[] getSeeds() {
        return new String[0];
    }

    @Override
    public Class<? extends Crawler> getControllerClass() {
        return null;
    }
}

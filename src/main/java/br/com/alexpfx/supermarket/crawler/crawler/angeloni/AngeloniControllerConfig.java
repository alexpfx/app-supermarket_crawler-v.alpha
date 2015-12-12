package br.com.alexpfx.supermarket.crawler.crawler.angeloni;

import br.com.alexpfx.supermarket.crawler.crawler.ControllerConfig;
import br.com.alexpfx.supermarket.crawler.crawler.Crawler;

/**
 * Created by alexandre on 06/12/2015.
 */
public class AngeloniControllerConfig implements ControllerConfig {
    public String getStorageFolder() {
        return "/data/crawl/root/";
    }

    public int getNumberOfCrawlers() {
        return 1;
    }

    public String[] getSeeds() {
        return new String[]{"http://www.angeloni.com.br/super/index"};
    }

    public Class<? extends Crawler> getControllerClass() {
        return AngeloniCrawler.class;
    }
}

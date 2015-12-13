package br.com.alexpfx.supermarket.crawler.crawler.camilo;

import br.com.alexpfx.supermarket.crawler.crawler.Crawler;
import br.com.alexpfx.supermarket.crawler.crawler.CrawlerControllerConfig;

/**
 * Created by alexandre on 12/12/2015.
 */
public class MercadoRibeiraoCrawlerControllerConfig implements CrawlerControllerConfig{

    @Override
    public String getStorageFolder() {
        return "data/mercadoribeirao/root";
    }

    @Override
    public int getNumberOfCrawlers() {
        return 1;
    }

    @Override
    public String[] getSeeds() {
        return new String[]{"https://www.mercadoribeirao.com.br/index.php"};
    }

    @Override
    public Class<? extends Crawler> getControllerClass() {
        return MercadoRibeiraoCrawler.class;
    }
}

package br.com.alexpfx.supermarket.crawler;

import br.com.alexpfx.supermarket.crawler.controller.CrawlerStarter;
import br.com.alexpfx.supermarket.crawler.controller.angeloni.AngeloniControllerConfig;

/**
 * Created by alexandre on 06/12/2015.
 */
public class Main {
    public static void main(String[] args) {
        CrawlerStarter c = new CrawlerStarter(new AngeloniControllerConfig());

        c.start();

    }

}

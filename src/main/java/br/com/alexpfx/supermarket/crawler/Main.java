package br.com.alexpfx.supermarket.crawler;

import br.com.alexpfx.supermarket.crawler.crawler.ControllerConfig;
import br.com.alexpfx.supermarket.crawler.crawler.CrawlerStarter;
import br.com.alexpfx.supermarket.crawler.crawler.angeloni.AngeloniControllerConfig;
import com.firebase.client.*;

/**
 * Created by alexandre on 06/12/2015.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        new Main().save();

//        c.start();
    }

    public void save() throws InterruptedException {
        Firebase.setDefaultConfig(new Config());
        final Firebase refri = new Firebase("https://supermarketcrawler.firebaseIO.com/");

        ControllerConfig config = new AngeloniControllerConfig();
        CrawlerStarter starter = new CrawlerStarter(config);
        starter.start();

    }
}


package br.com.alexpfx.supermarket.crawler;

import br.com.alexpfx.supermarket.crawler.controller.ControllerConfig;
import br.com.alexpfx.supermarket.crawler.controller.CrawlerStarter;
import br.com.alexpfx.supermarket.crawler.controller.angeloni.AngeloniControllerConfig;
import br.com.alexpfx.supermarket.crawler.model.database.Crud;
import br.com.alexpfx.supermarket.crawler.model.to.ProductInfoTO;
import com.firebase.client.*;
import org.apache.log4j.BasicConfigurator;

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


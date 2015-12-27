package br.com.alexpfx.supermarket.crawler;

import br.com.alexpfx.supermarket.crawler.crawler.CrawlerControllerConfig;
import br.com.alexpfx.supermarket.crawler.crawler.CrawlerStarter;
import br.com.alexpfx.supermarket.crawler.crawler.mercadoribeirao.MercadoRibeiraoCrawlerControllerConfig;
import com.firebase.client.*;
import org.apache.log4j.BasicConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by alexandre on 06/12/2015.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        new Main().runItAll();

//        c.start();
    }


    public void runItAll() throws InterruptedException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring.xml");
        CrawlerStarter starter = context.getBean(CrawlerStarter.class);

//        Firebase.setDefaultConfig(new Config());
//        final Firebase refri = new Firebase("https://supermarketcrawler.firebaseIO.com/");

//        CrawlerControllerConfig config = new AngeloniCrawlerControllerConfig();
//        CrawlerStarter starter = new CrawlerStarter(config);
//        starter.start();

        starter.start();

    }
}


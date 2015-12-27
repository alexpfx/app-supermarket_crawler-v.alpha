package br.com.alexpfx.supermarket.crawler.jaunt;

/**
 * Created by alexandre on 27/12/2015.
 */
public class Crawler implements Runnable {


    CrawlerImp crawlerImp;

    public Crawler(CrawlerImp crawlerImp) {
        this.crawlerImp = crawlerImp;
    }

    @Override
    public void run() {
        crawlerImp.run();
    }
}

package br.com.alexpfx.supermarket.crawler.jaunt;

/**
 * Created by alexandre on 27/12/2015.
 */
public class CrawlerRunner implements Runnable {

    Crawler crawler;

    public CrawlerRunner(Crawler crawler) {
        this.crawler = crawler;
    }

    @Override
    public void run() {
        crawler.crawl();
    }
}

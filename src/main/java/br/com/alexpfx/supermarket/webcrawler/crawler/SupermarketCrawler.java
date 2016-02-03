package br.com.alexpfx.supermarket.webcrawler.crawler;

import br.com.alexpfx.supermarket.webcrawler.crawler.collector.ItemsCollector;
import br.com.alexpfx.supermarket.webcrawler.crawler.collector.UrlsCollector;
import br.com.alexpfx.supermarket.webcrawler.listeners.CrawlerListener;
import br.com.alexpfx.supermarket.webcrawler.to.TransferObject;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by alexandre on 27/12/2015.
 */
public class SupermarketCrawler implements Crawler {

    private CrawlerListener listener = CrawlerListener.EMPTY;

    private StopCondition stopCondition = StopCondition.EMTPY;

    private long startTime;

    private UrlsCollector urlsCollector;

    private ItemsCollector itemsCollector;


    private List<String> startUrls;

    public SupermarketCrawler(UrlsCollector urlsCollector, ItemsCollector itemsCollector, List<String> startUrls) {
        this.urlsCollector = urlsCollector;
        this.itemsCollector = itemsCollector;
        this.startUrls = startUrls;
    }

    @Override
    public void crawl() {
        startTime = System.currentTimeMillis();
        List<String> urlsToVisit = urlsCollector.collect(startUrls);
        Iterator<String> iterator = urlsToVisit.iterator();
        boolean stop = false;
        int i = 0;
        int size = urlsToVisit.size();
        while (iterator.hasNext() && !stop) {
            String url = iterator.next();
            System.out.println();
            System.out.println(url);
            System.out.println();
            List<TransferObject> collected = itemsCollector.collect(Collections.singletonList(url));
            notifyListeners(collected);
            stop = evaluateStopCondition(url, i++, size);
        }
    }

    private boolean evaluateStopCondition(String link, int actual, int size) {
        stopCondition.init(startTime, link, actual, size);
        return stopCondition.isReached();
    }


    private void notifyListeners(List<TransferObject> products) {
        products.forEach(product -> {
            listener.itemExtracted(product);
        });
    }


    @Override
    public void setListener(CrawlerListener listener) {
        this.listener = listener;
    }


    public void setStopCondition(StopCondition stopCondition) {
        this.stopCondition = stopCondition;
    }


}

package br.com.alexpfx.supermarket.webcrawler.crawler;

import br.com.alexpfx.supermarket.webcrawler.listeners.CrawlerListener;
import br.com.alexpfx.supermarket.webcrawler.to.TransferObject;
import com.jaunt.Document;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by alexandre on 27/12/2015.
 */
public abstract class AbstractCrawler implements Crawler {

    private CrawlerListener listener = CrawlerListener.EMPTY;

    private StopCondition stopCondition = StopCondition.EMTPY;

    protected abstract FlowControl extractUrlsToVisit(List<String> outputUrlList, Document document);

    private String rootUrl;

    private UserAgent userAgent;
    private long startTime;

    public AbstractCrawler(UserAgent userAgent, String rootUrl) {
        this.userAgent = userAgent;
        this.rootUrl = rootUrl;
    }

    @Override
    public void crawl() {
        startTime = System.currentTimeMillis();
        Document doc = null;
        try {
            doc = userAgent.visit(rootUrl);
        } catch (ResponseException e) {
            throw new IllegalArgumentException("erro ao visitar Root Url", e);
        }
        List<String> urlsToVisit = new ArrayList<>();
        FlowControl flowControl = FlowControl.REPEAT;
        while (FlowControl.REPEAT == flowControl){
            extractUrlsToVisit(urlsToVisit, doc);
        }

        Iterator<String> iterator = urlsToVisit.iterator();
        boolean stop = false;
        int i = 0;
        int size = urlsToVisit.size();
        while (iterator.hasNext() && !stop) {
            String link = iterator.next();
            List<TransferObject> products = null;
            try {
                products = extract(userAgent.visit(link));
            } catch (ResponseException e) {
                e.printStackTrace();
            }
            notifyListeners(products);
            stop = evaluateStopCondition(link, i++, size);
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

    protected abstract List<TransferObject> extract(Document visit);

    public void setStopCondition(StopCondition stopCondition) {
        this.stopCondition = stopCondition;
    }


}

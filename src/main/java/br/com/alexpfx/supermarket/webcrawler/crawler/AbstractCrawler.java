package br.com.alexpfx.supermarket.webcrawler.crawler;

import br.com.alexpfx.supermarket.webcrawler.listeners.CrawlerListener;
import br.com.alexpfx.supermarket.webcrawler.to.TransferObject;
import com.jaunt.Document;
import com.jaunt.ResponseException;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by alexandre on 27/12/2015.
 */
public abstract class AbstractCrawler implements Crawler {

    private CrawlerListener listener = CrawlerListener.EMPTY;

    private StopCondition stopCondition = StopCondition.EMTPY;

    protected abstract FlowControl extractUrlsToVisit(List<String> outputUrlList, Document document);


    private long startTime;

    private Visitor visitor;

    public AbstractCrawler(Visitor visitor) {
        this.visitor = visitor;
    }

    @Override
    public void crawl() {
        startTime = System.currentTimeMillis();
        List<String> urlsToVisit = Collections.emptyList();
        try {
            urlsToVisit = visitor.collect();
        } catch (ResponseException e) {
            e.printStackTrace();
        }

        Iterator<String> iterator = urlsToVisit.iterator();
        boolean stop = false;
        int i = 0;
        int size = urlsToVisit.size();
        while (iterator.hasNext() && !stop) {
            String link = iterator.next();
            List<TransferObject> products = null;
            try {
                //TODO: ruim
                products = extract(visitor.getUserAgent().visit(link));
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

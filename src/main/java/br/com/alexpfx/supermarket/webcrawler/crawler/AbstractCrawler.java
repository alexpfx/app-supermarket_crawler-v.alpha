package br.com.alexpfx.supermarket.webcrawler.crawler;

import br.com.alexpfx.supermarket.webcrawler.listeners.CrawlerListener;
import br.com.alexpfx.supermarket.webcrawler.listeners.ProductExtractedListener;
import br.com.alexpfx.supermarket.webcrawler.to.TransferObject;
import com.jaunt.Document;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

import java.util.Iterator;
import java.util.List;

/**
 * Created by alexandre on 27/12/2015.
 */
public abstract class AbstractCrawler implements Crawler {

    private ProductExtractedListener productExtractedListener;

    private StopCondition stopCondition = StopCondition.EMTPY;

    protected abstract List<String> extractSubPages(Document document);

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
            throw new IllegalArgumentException("erro ao visitar Root Url");
        }
        List<String> links = extractSubPages(doc);
        Iterator<String> iterator = links.iterator();
        boolean stop = false;
        int i = 0;
        int size = links.size();
        while (iterator.hasNext() && !stop) {
            String link = iterator.next();
            List<TransferObject> products = null;
            try {
                products = extract(userAgent.visit(link));
            } catch (ResponseException e) {
                e.printStackTrace();
            }
            stop = evaluateStopCondition(link, i, size);
            notifyListeners(products);
            i++;
        }
    }

    private boolean evaluateStopCondition(String link, int actual, int size) {
        stopCondition.init(startTime, link, actual, size);
        return stopCondition.evaluate();
    }


    private void notifyListeners(List<TransferObject> products) {
        if (productExtractedListener == null) {
            return;
        }
        products.forEach(product -> {
            productExtractedListener.itemExtracted(product);
        });
    }


    @Override
    public void setListener(CrawlerListener listener) {
        this.productExtractedListener = (ProductExtractedListener) listener;
    }

    protected abstract List<TransferObject> extract(Document visit);


}

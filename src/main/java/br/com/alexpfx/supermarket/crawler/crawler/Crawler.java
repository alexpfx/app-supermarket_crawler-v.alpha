package br.com.alexpfx.supermarket.crawler.crawler;

import br.com.alexpfx.supermarket.crawler.model.to.ProductInfoTO;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.regex.Pattern;

/**
 * Created by alexandre on 04/12/2015.
 */
public abstract class Crawler extends WebCrawler {

    {
        init();
    }

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp3|zip|gz))$");
    private CrawlerModel crawlerModel;
    private CrawlerListener listener;

    public void setCrawlerModel(CrawlerModel crawlerModel) {
        this.crawlerModel = crawlerModel;
    }

    public void setListener(CrawlerListener listener) {
        this.listener = listener;
    }

    public abstract void init ();


    @Override
    public boolean shouldVisit(Page page, WebURL url) {
        if (crawlerModel == null || listener == null) {
            return false;
        }

        String href = url.getURL().toLowerCase();
        if (FILTERS.matcher(href).matches()) {
            return false;
        }
        return crawlerModel.shouldVisit(page, url);
    }

    @Override
    public void visit(Page page) {
        if (!crawlerModel.isProductPage(page.getWebURL().getURL())) {
            return;
        }

        ProductInfoTO productInfo = crawlerModel.extractProduct(page);
        if (productInfo != null) {
            try{
                listener.onProductVisit(productInfo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}


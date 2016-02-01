package br.com.alexpfx.supermarket.webcrawler.crawler.collector;

import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.CrawlerAPI;
import br.com.alexpfx.supermarket.webcrawler.to.TransferObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 18/01/2016.
 */
public class ItemsCollector<A extends CrawlerAPI<?>> extends AbstractCollector<TransferObject, A> {

    private ExtractionRules<TransferObject> extractionRules;


    public ItemsCollector(A crawlerAPI, ExtractionRules<TransferObject> extractionRules) {
        super(crawlerAPI);
        this.extractionRules = extractionRules;
    }

    @Override
    protected List<TransferObject> doCollect(List<String> urls) {
        return evaluate(urls);
    }

    private List<TransferObject> evaluate(List<String> urls) {
        List<TransferObject> list = new ArrayList<>();
        urls.forEach(url -> {
            List<TransferObject> eList = extractionRules.extract(getCrawlerAPI().visit(url));
            eList.forEach(e -> {
                list.add(e);
            });
        });
        return list;
    }
}



package br.com.alexpfx.supermarket.webcrawler.crawler.collector;

import br.com.alexpfx.supermarket.webcrawler.crawler.api.JSoupHandler;
import br.com.alexpfx.supermarket.webcrawler.to.TransferObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 18/01/2016.
 */
public class ItemsCollector extends AbstractCollector<TransferObject> {

    private CollectorRule<TransferObject> collectorRule;

    public ItemsCollector(CollectorRule<TransferObject> collectorRule) {
        this.collectorRule = collectorRule;
    }

    @Override
    protected List<TransferObject> doCollect(List<String> urls) {
        return evaluate(urls);
    }

    private List<TransferObject> evaluate(List<String> urls) {
        List<TransferObject> list = new ArrayList<>();
        urls.forEach(url -> {
            List<TransferObject> eList = collectorRule.evaluate(JSoupHandler.visit(url));
            eList.forEach(e -> {
                list.add(e);
            });
        });
        return list;
    }
}



package br.com.alexpfx.supermarket.webcrawler.crawler;

import br.com.alexpfx.supermarket.webcrawler.to.TransferObject;
import com.jaunt.ResponseException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 18/01/2016.
 */
public class ItemsCollector extends AbstractCollector<TransferObject> {

    private String url;

    public ItemsCollector(String url) {
        this.url = url;
    }

    @Override
    protected List<TransferObject> doCollect(List<String> urls) {
        return evaluate(urls);
    }

    private List<TransferObject> evaluate(List<String> urls) {
        List<TransferObject> list = new ArrayList<>();
        urls.forEach(url -> {
            try {
                List<TransferObject> eList = collectorRule.evaluate(userAgent.visit(url));
                eList.forEach(e -> {
                    collectorListener.collected(e);
                    list.add(e);
                });
            } catch (ResponseException e) {
                //TODO LOG
            }
        });
        return list;
    }
}



package br.com.alexpfx.supermarket.webcrawler.crawler;

import br.com.alexpfx.supermarket.webcrawler.to.TransferObject;
import com.jaunt.Elements;
import com.jaunt.ResponseException;

import java.util.Collections;
import java.util.List;

/**
 * Created by alexandre on 18/01/2016.
 */
public class ItemsCollector extends AbstractCollector <TransferObject>  {

    private String url;

    public ItemsCollector(String url) {
        this.url = url;
    }

    @Override
    protected List<TransferObject> doCollect() {
        return evaluate();
    }

    private List<TransferObject> evaluate() {
        try {
            return collectorRule.evaluate(userAgent.visit(url));
        } catch (ResponseException e) {
            //LOG
            return Collections.EMPTY_LIST;
        }
    }
}



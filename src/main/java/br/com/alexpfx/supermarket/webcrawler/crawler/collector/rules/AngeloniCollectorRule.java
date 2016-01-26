package br.com.alexpfx.supermarket.webcrawler.crawler.collector.rules;

import br.com.alexpfx.supermarket.webcrawler.crawler.collector.CollectorRule;
import br.com.alexpfx.supermarket.webcrawler.to.TransferObject;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by alexandre on 25/01/2016.
 */
public class AngeloniCollectorRule implements CollectorRule<TransferObject> {
    @Override
    public List<TransferObject> evaluate(Document doc) {
        return null;
    }
}

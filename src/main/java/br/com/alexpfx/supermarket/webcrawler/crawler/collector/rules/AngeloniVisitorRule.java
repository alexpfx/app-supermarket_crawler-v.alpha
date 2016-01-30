package br.com.alexpfx.supermarket.webcrawler.crawler.collector.rules;

import br.com.alexpfx.supermarket.webcrawler.crawler.collector.CollectorRule;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 25/01/2016.
 */
public class AngeloniVisitorRule implements CollectorRule<String> {
    @Override
    public List<String> evaluate(String htmlCode) {
        List<String> list = new ArrayList<String>();
        Element doc = null;
        org.jsoup.select.Elements elements = doc.select("a.lnkTp01 ");

        org.jsoup.select.Elements lnkTp02 = doc.select("a.lnkTp02 ");
        elements.addAll(lnkTp02);

        elements.forEach(element1 -> {
            String href = element1.attr("abs:href");
            list.add(href);
        });
        return list;

    }
}


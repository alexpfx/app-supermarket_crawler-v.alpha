package br.com.alexpfx.supermarket.webcrawler.crawler.collector.rules;

import br.com.alexpfx.supermarket.webcrawler.crawler.collector.CollectorRule;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 25/01/2016.
 */
public class RibeiraoVisitorRule implements CollectorRule {
    @Override
    public List evaluate(Document doc) {
        List<String> list = new ArrayList<>();
        Elements submenu = doc.select("a.new_sub_menu");
        submenu.forEach(element1 -> {
            String href = element1.attr("abs:href");
            list.add(href.concat("&pageNum=VER-TUDO"));
        });
        return list;
    }
}

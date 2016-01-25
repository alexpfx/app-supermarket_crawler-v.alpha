package br.com.alexpfx.supermarket.webcrawler.crawler.impl;

import br.com.alexpfx.supermarket.webcrawler.crawler.AbstractCrawler;
import br.com.alexpfx.supermarket.webcrawler.crawler.CollectorRule;
import br.com.alexpfx.supermarket.webcrawler.crawler.ItemsCollector;
import br.com.alexpfx.supermarket.webcrawler.crawler.UrlsCollector;
import br.com.alexpfx.supermarket.webcrawler.to.ProdutoSuperMercadoTO;
import br.com.alexpfx.supermarket.webcrawler.to.TransferObject;
import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.NotFound;
import com.jaunt.UserAgent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by alexandre on 14/01/2016.
 */
public class AngeloniCrawler extends AbstractCrawler {
    private static final String ROOT_URL = "http://www.angeloni.com.br/super/index";

    private static final CollectorRule VISITOR_RULE = doc -> {
        List<String> list = new ArrayList<String>();
        org.jsoup.select.Elements elements = doc.select("a.lnkTp01 ");

        org.jsoup.select.Elements lnkTp02 = doc.select("a.lnkTp02 ");
        elements.addAll(lnkTp02);

        elements.forEach(element1 -> {
            String href = element1.attr("abs:href");
            list.add(href);
        });
        return list;
    };

    private static final CollectorRule<TransferObject> ITEM_RULE = doc -> {
        org.jsoup.select.Elements lstProd = doc.select("ul.lstProd");
        return Collections.singletonList(new ProdutoSuperMercadoTO());
    };


    public AngeloniCrawler() {
        super(new UrlsCollector(VISITOR_RULE), new ItemsCollector(ITEM_RULE), Collections.singletonList(ROOT_URL));
    }

}

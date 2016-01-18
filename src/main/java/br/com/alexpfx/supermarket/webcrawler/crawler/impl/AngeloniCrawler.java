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
        Elements elements = doc.findEach("<a class='lnkTp01 '>");
        Element lnkTp02 = doc.findEach("<a class='lnkTp02 '>");
        elements.addChildren(0, lnkTp02.getChildNodes());
        elements.findEach("<a>").forEach(element -> {
            String href = null;
            try {
                href = element.getAt("href");
                list.add(href);
            } catch (NotFound notFound) {
                notFound.printStackTrace();
            }
        });
        return list;
    };

    private static final CollectorRule<TransferObject> ITEM_RULE = doc -> {
        Elements lstProd = doc.findEach("<ul class='lstProd '");
        return Collections.singletonList(new ProdutoSuperMercadoTO());
    };


    public AngeloniCrawler(UserAgent userAgent) {
        super(new UrlsCollector(VISITOR_RULE), new ItemsCollector(ITEM_RULE), userAgent, Collections.singletonList(ROOT_URL));
    }

    /*
    private void extrair(Document document, List<String> list) {
        Elements submenu = document.findEach("<a class='lnkTp01 '>");

        submenu.findEvery("<a>").forEach(element -> {
            String href = null;
            try {
                href = element.getAt("href");
                list.add(href);
            } catch (NotFound notFound) {
                notFound.printStackTrace();
            }
        });
    }
    */

}

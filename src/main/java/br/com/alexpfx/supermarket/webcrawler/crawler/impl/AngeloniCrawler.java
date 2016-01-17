package br.com.alexpfx.supermarket.webcrawler.crawler.impl;

import br.com.alexpfx.supermarket.webcrawler.crawler.AbstractCrawler;
import br.com.alexpfx.supermarket.webcrawler.crawler.FlowControl;
import br.com.alexpfx.supermarket.webcrawler.crawler.Visitor;
import br.com.alexpfx.supermarket.webcrawler.crawler.VisitorRule;
import br.com.alexpfx.supermarket.webcrawler.factory.UserAgentFactory;
import br.com.alexpfx.supermarket.webcrawler.to.TransferObject;
import com.jaunt.Document;
import com.jaunt.Elements;
import com.jaunt.NotFound;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by alexandre on 14/01/2016.
 */
public class AngeloniCrawler extends AbstractCrawler {
    private static final String ROOT_URL = "http://www.angeloni.com.br/super/index";

    private static final VisitorRule VISITOR_RULE = doc -> {
        return null;
    };


    public AngeloniCrawler(UserAgentFactory userAgentFactory) {
        super(new Visitor(userAgentFactory.createUserAgent(), VISITOR_RULE, Collections.singletonList(ROOT_URL)));
    }

    @Override
    protected FlowControl extractUrlsToVisit(List<String> outputUrlList, Document document) {
        List<String> list = new ArrayList<>();
        extrair(document, list);
        return FlowControl.DONE;
    }

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

    @Override
    protected List<TransferObject> extract(Document visit) {
        return null;
    }
}

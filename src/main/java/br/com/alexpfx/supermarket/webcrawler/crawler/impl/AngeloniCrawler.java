package br.com.alexpfx.supermarket.webcrawler.crawler.impl;

import br.com.alexpfx.supermarket.webcrawler.crawler.AbstractCrawler;
import br.com.alexpfx.supermarket.webcrawler.crawler.FlowControl;
import br.com.alexpfx.supermarket.webcrawler.factory.UserAgentFactory;
import br.com.alexpfx.supermarket.webcrawler.to.TransferObject;
import com.jaunt.Document;
import com.jaunt.Elements;
import com.jaunt.NotFound;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 14/01/2016.
 */
public class AngeloniCrawler extends AbstractCrawler {
    private static final String ROOT_URL = "http://www.angeloni.com.br/super/index";

    public AngeloniCrawler(UserAgentFactory userAgentFactory) {
        super(userAgentFactory.createUserAgent(), ROOT_URL);
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

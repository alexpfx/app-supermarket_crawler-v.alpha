package br.com.alexpfx.supermarket.webcrawler.crawler.apibridge;

import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.parse.ParserAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.visitor.VisitorAPI;

/**
 * Created by alexandre on 30/01/2016.
 */
public class CrawlerAPIImpl<D> implements CrawlerAPI<D> {

    private VisitorAPI visitorAPI;
    private ParserAPI<D> parserAPI;

    public CrawlerAPIImpl(VisitorAPI visitorAPI, ParserAPI<D> parserAPI) {
        this.visitorAPI = visitorAPI;
        this.parserAPI = parserAPI;
    }

    @Override
    public String visit(String url) {
        return visitorAPI.visit(url);
    }

    @Override
    public D parse(String htmlCode) {
        return parserAPI.parseDocument(htmlCode);
    }
}

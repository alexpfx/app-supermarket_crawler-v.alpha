package br.com.alexpfx.supermarket.webcrawler.crawler.apibridge;

import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.parse.ParserAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.visitor.VisitorAPI;

/**
 * Created by alexandre on 30/01/2016.
 */
public class CrawlerAPIImpl implements CrawlerAPI {

    private VisitorAPI visitorAPI;
    private ParserAPI parserAPI;

    public CrawlerAPIImpl(VisitorAPI visitorAPI, ParserAPI parserAPI) {
        this.visitorAPI = visitorAPI;
        this.parserAPI = parserAPI;
    }

    @Override
    public String visit(String url) {
        return visitorAPI.visit(url);
    }

    @Override
    public <T> T parse(String htmlCode) {
        return (T) parserAPI.parseDocument(htmlCode);
    }
}

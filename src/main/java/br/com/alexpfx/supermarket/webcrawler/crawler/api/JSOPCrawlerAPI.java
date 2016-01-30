package br.com.alexpfx.supermarket.webcrawler.crawler.api;

import br.com.alexpfx.supermarket.webcrawler.crawler.api.parse.JSoupParseAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.api.parse.ParserAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.api.visitor.JsoupVisitorAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.api.visitor.VisitorAPI;

/**
 * Created by alexandre on 30/01/2016.
 */
public class JSOPCrawlerAPI implements CrawlerAPI {
    @Override
    public VisitorAPI getVisitorApi() {
        return new JsoupVisitorAPI();
    }

    @Override
    public ParserAPI getDocumentParseApi() {
        return new JSoupParseAPI();
    }
}

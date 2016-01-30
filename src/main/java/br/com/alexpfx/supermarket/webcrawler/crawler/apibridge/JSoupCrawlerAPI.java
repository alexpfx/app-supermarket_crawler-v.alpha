package br.com.alexpfx.supermarket.webcrawler.crawler.apibridge;

import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.parse.JSoupParseAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.parse.ParserAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.visitor.JsoupVisitorAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.visitor.VisitorAPI;

/**
 * Created by alexandre on 30/01/2016.
 */
public class JSoupCrawlerAPI implements CrawlerAPI {
    @Override
    public VisitorAPI getVisitorApi() {
        return new JsoupVisitorAPI();
    }

    @Override
    public ParserAPI getDocumentParseApi() {
        return new JSoupParseAPI();
    }
}

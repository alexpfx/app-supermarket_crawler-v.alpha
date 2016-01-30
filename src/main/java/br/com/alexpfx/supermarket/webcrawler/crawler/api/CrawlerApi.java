package br.com.alexpfx.supermarket.webcrawler.crawler.api;

import br.com.alexpfx.supermarket.webcrawler.crawler.api.htmlparse.ParserAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.api.visitor.VisitorApi;

/**
 * Created by alexandre on 30/01/2016.
 */
public interface CrawlerApi {
    VisitorApi getVisitorApi ();
    ParserAPI getDocumentParseApi ();
}

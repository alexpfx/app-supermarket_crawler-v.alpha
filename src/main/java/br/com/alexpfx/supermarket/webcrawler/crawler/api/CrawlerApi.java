package br.com.alexpfx.supermarket.webcrawler.crawler.api;

import br.com.alexpfx.supermarket.webcrawler.crawler.api.parse.ParserAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.api.visitor.VisitorAPI;

/**
 * Created by alexandre on 30/01/2016.
 */
public interface CrawlerAPI {
    VisitorAPI getVisitorApi ();
    ParserAPI getDocumentParseApi ();
}

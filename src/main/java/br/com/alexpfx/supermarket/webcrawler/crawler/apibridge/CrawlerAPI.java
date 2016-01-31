package br.com.alexpfx.supermarket.webcrawler.crawler.apibridge;

import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.parse.ParserAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.visitor.VisitorAPI;

/**
 * Created by alexandre on 30/01/2016.
 */
public interface CrawlerAPI {

    String visit (String url);

    <T> T parse (String htmlCode);

}

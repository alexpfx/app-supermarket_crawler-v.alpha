package br.com.alexpfx.supermarket.webcrawler.crawler.api;

import br.com.alexpfx.supermarket.webcrawler.crawler.api.parse.JauntParserAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.api.parse.ParserAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.api.visitor.JauntVisitorAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.api.visitor.VisitorAPI;
import com.jaunt.UserAgent;

/**
 * Created by alexandre on 29/01/2016.
 */
public class JauntCrawlerAPI implements CrawlerAPI {

    @Override
    public VisitorAPI getVisitorApi() {
        return new JauntVisitorAPI(new UserAgent());
    }

    @Override
    public ParserAPI getDocumentParseApi() {
        return new JauntParserAPI(new UserAgent());
    }
}

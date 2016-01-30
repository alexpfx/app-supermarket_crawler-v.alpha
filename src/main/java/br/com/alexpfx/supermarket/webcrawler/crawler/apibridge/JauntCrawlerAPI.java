package br.com.alexpfx.supermarket.webcrawler.crawler.apibridge;

import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.parse.JauntParserAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.parse.ParserAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.visitor.JauntVisitorAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.visitor.VisitorAPI;
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

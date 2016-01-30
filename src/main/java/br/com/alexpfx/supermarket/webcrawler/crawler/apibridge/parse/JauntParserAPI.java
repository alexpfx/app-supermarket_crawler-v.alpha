package br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.parse;

import com.jaunt.Document;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

/**
 * Created by alexandre on 30/01/2016.
 */
public class JauntParserAPI implements ParserAPI<Document> {
    private UserAgent userAgent;

    public JauntParserAPI(UserAgent userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public Document parseDocument(String htmlCode) {
        try {
            return userAgent.openContent(htmlCode);
        } catch (ResponseException e) {
            return null;
        }
    }
}



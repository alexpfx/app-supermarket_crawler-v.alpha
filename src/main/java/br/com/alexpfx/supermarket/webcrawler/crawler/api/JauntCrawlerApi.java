package br.com.alexpfx.supermarket.webcrawler.crawler.api;

import com.jaunt.Document;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

/**
 * Created by alexandre on 29/01/2016.
 */
public class JauntCrawlerApi implements OldCrawlerApi {

    UserAgent userAgent;

    public JauntCrawlerApi() {
        this.userAgent = createUserAgent();
    }

    private UserAgent createUserAgent() {
        UserAgent u = new UserAgent();
        u.settings.charset = CHARSET;
        return u;
    }


    @Override
    public String crawl(String url) {
        try {
            return userAgent.visit(url).innerHTML();
        } catch (ResponseException e) {
            return "";
        }
    }

    @Override
    public <T> T getDocument(String htmlCode, Class<T> type) {
        try {
            Document document = userAgent.openContent(htmlCode);
            return (T) document;
        } catch (ResponseException e) {
            // Ideally return an empty but not null object.
            return null;
        }
    }
}

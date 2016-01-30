package br.com.alexpfx.supermarket.webcrawler.crawler.api;

import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

/**
 * Created by alexandre on 29/01/2016.
 */
public class JauntApiHandlerImpl implements CrawlerApiHandler {
    UserAgent userAgent;

    public JauntApiHandlerImpl(UserAgent userAgent) {
        this.userAgent = userAgent;
    }


    @Override
    public String visit(String url) {
        try {
            return userAgent.visit(url).toString();
        } catch (ResponseException e) {
            return "";
        }
    }
}

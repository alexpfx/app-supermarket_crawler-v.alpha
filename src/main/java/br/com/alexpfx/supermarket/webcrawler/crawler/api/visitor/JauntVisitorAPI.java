package br.com.alexpfx.supermarket.webcrawler.crawler.api.visitor;

import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

/**
 * Created by alexandre on 30/01/2016.
 */
public class JauntVisitorAPI implements VisitorAPI {

    private UserAgent userAgent;

    public JauntVisitorAPI(UserAgent userAgent) {
        this.userAgent = userAgent;
    }


    @Override
    public String visit(String url) {
        try {
            return userAgent.visit(url).innerHTML();
        } catch (ResponseException e) {
            return "";
        }
    }
}
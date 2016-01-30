package br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.visitor;

import org.jsoup.Jsoup;

import java.io.IOException;

import static br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.Constants.CHARSET;
import static br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.Constants.TIMEOUT;
import static br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.Constants.USER_AGENT;

/**
 * Created by alexandre on 30/01/2016.
 */
public class JsoupVisitorAPI implements VisitorAPI {



    @Override
    public String visit(String url) {
        try {
            return Jsoup.connect(url).timeout(TIMEOUT).userAgent(USER_AGENT).postDataCharset(CHARSET).get().html();
        } catch (IOException e) {
            return "";
        }

    }
}

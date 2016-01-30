package br.com.alexpfx.supermarket.webcrawler.crawler.api.visitor;

import org.jsoup.Jsoup;

import java.io.IOException;

import static br.com.alexpfx.supermarket.webcrawler.crawler.api.Constants.CHARSET;
import static br.com.alexpfx.supermarket.webcrawler.crawler.api.Constants.TIMEOUT;
import static br.com.alexpfx.supermarket.webcrawler.crawler.api.Constants.USER_AGENT;

/**
 * Created by alexandre on 30/01/2016.
 */
public class JsoupVisitorApi implements VisitorApi {



    @Override
    public String visit(String url) {
        try {
            return Jsoup.connect(url).timeout(TIMEOUT).userAgent(USER_AGENT).postDataCharset(CHARSET).get().html();
        } catch (IOException e) {
            return "";
        }

    }
}

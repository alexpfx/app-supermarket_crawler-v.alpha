package br.com.alexpfx.supermarket.webcrawler.crawler.api;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by alexandre on 24/01/2016.
 */
public class JsopHandler {


    public static final Document visit(String url) {
        try {
            Document document = Jsoup.connect(url)
                    .timeout(20000)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .get();
            return document;
        } catch (IOException e) {
            throw new RuntimeException(url, e);
        }
    }


}

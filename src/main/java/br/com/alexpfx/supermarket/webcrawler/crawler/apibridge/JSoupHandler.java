package br.com.alexpfx.supermarket.webcrawler.crawler.apibridge;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by alexandre on 24/01/2016.
 */
public class JSoupHandler {

    public static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";

    public static final Document connectAndGet(String url) {
        try {
            Document document = Jsoup.connect(url)
                                     .timeout(20000)
                                     .userAgent(USER_AGENT)
                                     .get();
            return document;
        } catch (IOException e) {
            throw new RuntimeException(url, e);
        }
    }


}

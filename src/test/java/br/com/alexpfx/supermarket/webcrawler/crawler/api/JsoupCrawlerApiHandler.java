package br.com.alexpfx.supermarket.webcrawler.crawler.api;

import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * Created by alexandre on 29/01/2016.
 */
public class JsoupCrawlerApiHandler implements CrawlerApiHandler {
    public static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";

    @Override
    public String visit(String url) {
        try {
            return Jsoup.connect(url).timeout(20000)
                    .userAgent(USER_AGENT).get().toString();
        } catch (IOException e) {
            //LOG
            return "";
        }
    }
}

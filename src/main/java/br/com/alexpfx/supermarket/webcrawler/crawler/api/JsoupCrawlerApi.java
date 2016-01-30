package br.com.alexpfx.supermarket.webcrawler.crawler.api;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.function.Function;

/**
 * Created by alexandre on 29/01/2016.
 */
public class JsoupCrawlerApi implements OldCrawlerApi {
    public static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";


    @Override
    public String crawl(String url) {
        return connect.apply(url).html();
    }

    //I made this static because of the peculiarity of api that is static and because I have to
    //set some config parameters.
    private static Function<String, Document> connect = url -> {
        try {
            return Jsoup.connect(url).timeout(TIMEOUT).userAgent(USER_AGENT).postDataCharset(CHARSET).get();
        } catch (IOException e) {
            return new Document("");
        }
    };

    private static Function<String, Document> parse = html -> {
        Document doc = Jsoup.parse(html);
        doc.charset(Charset.forName(CHARSET));
        return doc;
    };


    @Override
    public <T> T getDocument(String htmlCode, Class<T> type) {
        return (T) parse.apply(htmlCode);
    }

}

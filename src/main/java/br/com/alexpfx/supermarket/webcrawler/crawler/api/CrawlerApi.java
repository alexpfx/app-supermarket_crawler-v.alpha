package br.com.alexpfx.supermarket.webcrawler.crawler.api;

/**
 * Created by alexandre on 29/01/2016.
 */
public interface CrawlerApi {
    String CHARSET = "ISO8859_1";
    int TIMEOUT = 20000;

    String visit (String url);

    <T> T getDocument (String htmlCode, Class <T> documentType);

}

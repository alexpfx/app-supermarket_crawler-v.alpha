package br.com.alexpfx.supermarket.webcrawler.crawler.api;

/**
 * Created by alexandre on 29/01/2016.
 */
public interface CrawlerApi {
    // common constants to serveral implementations.
    String CHARSET = "ISO8859_1";
    int TIMEOUT = 20000;

    /**
     * Visit an url and return contents in String format.
     *
     * @param url
     * @return
     */
    String visit (String url);

    /**
     * Parse an Html code into an Navigable object that is provided by the API.
     *
     * @param htmlCode
     * @param documentType
     * @param <T>
     * @return
     */
    <T> T getDocument (String htmlCode, Class <T> documentType);

}

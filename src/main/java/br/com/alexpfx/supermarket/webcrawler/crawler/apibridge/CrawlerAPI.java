package br.com.alexpfx.supermarket.webcrawler.crawler.apibridge;

/**
 * Created by alexandre on 30/01/2016.
 */
public interface CrawlerAPI<T> {

    String visit(String url);

    T parse(String htmlCode);

}

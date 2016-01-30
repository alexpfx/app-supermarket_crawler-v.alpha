package br.com.alexpfx.supermarket.webcrawler.crawler.api.htmlparse;

/**
 * Created by alexandre on 30/01/2016.
 */
public interface ParserAPI<T> {
    T parseDocument (String htmlCode);
}

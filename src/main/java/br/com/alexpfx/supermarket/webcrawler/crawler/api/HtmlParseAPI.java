package br.com.alexpfx.supermarket.webcrawler.crawler.api;

/**
 * Created by alexandre on 30/01/2016.
 */
public interface HtmlParseAPI<T> {
    T parseDocument (String htmlCode);
}

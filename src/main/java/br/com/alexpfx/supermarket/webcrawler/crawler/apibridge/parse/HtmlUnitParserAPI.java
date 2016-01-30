package br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.parse;

import com.gargoylesoftware.htmlunit.Page;

/**
 * Created by alexandre on 30/01/2016.
 */
public class HtmlUnitParserAPI implements ParserAPI<Page> {
    @Override
    public Page parseDocument(String htmlCode) {
        throw new IllegalArgumentException("Not implemented");
    }
}

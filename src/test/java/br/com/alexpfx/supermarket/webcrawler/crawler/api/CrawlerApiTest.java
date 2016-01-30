package br.com.alexpfx.supermarket.webcrawler.crawler.api;

import org.jsoup.nodes.Document;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by alexandre on 30/01/2016.
 */
public class CrawlerApiTest {
    public static final String URL = "http://www.angeloni.com.br/super/index?grupo=15022";
    @Test
    public void testVisit_Jaunt() throws Exception {
        JauntCrawlerApi jauntCrawlerApi = new JauntCrawlerApi();
        String r = jauntCrawlerApi.visit(URL);
        System.out.println(r);
        JsoupCrawlerApi jsoupCrawlerApi = new JsoupCrawlerApi();
        String r1 = jsoupCrawlerApi.visit(URL);

        jsoupCrawlerApi.getDocument(r1, Document.class);
        System.out.println(r1);



    }
}
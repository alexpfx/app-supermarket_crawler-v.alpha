package br.com.alexpfx.supermarket.webcrawler.crawler.api;

import org.jsoup.nodes.Document;
import org.junit.Test;

/**
 * Created by alexandre on 30/01/2016.
 */
public class CrawlerApiTest {
    public static final String URL = "http://www.angeloni.com.br/super/index?grupo=15022";
    @Test
    public void testVisit_Jaunt() throws Exception {
        JauntCrawlerApi jauntCrawlerApi = new JauntCrawlerApi();
        String r = jauntCrawlerApi.crawl(URL);
        System.out.println(r);
        JsoupCrawlerApi jsoupCrawlerApi = new JsoupCrawlerApi();
        String r1 = jsoupCrawlerApi.crawl(URL);

        jsoupCrawlerApi.getDocument(r1, Document.class);
        System.out.println(r1);



    }
}
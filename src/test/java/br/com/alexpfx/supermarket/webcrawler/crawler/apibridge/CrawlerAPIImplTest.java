package br.com.alexpfx.supermarket.webcrawler.crawler.apibridge;

import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.parse.JSoupParseAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.visitor.HtmlUnitVisitorAPI;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by alexandre on 30/01/2016.
 */
public class CrawlerAPIImplTest {

    CrawlerAPI <Document> crawlerAPI;

    @Before
    public void setUp() {
        crawlerAPI = new CrawlerAPIImpl(new HtmlUnitVisitorAPI(), new JSoupParseAPI());
    }

    @Test
    public void testVisit() throws Exception {
        String visit = crawlerAPI.visit("http://www.angeloni.com.br/super/index?grupo=20");
        assertNotNull(visit);
        assertNotEquals("", visit);
//        http://www.angeloni.com.br/super/index?grupo=17004
    }

    @Test
    public void testParse() throws Exception {
        Document document = crawlerAPI.parse("<html> <body> </body> </html>");
        assertNotNull(document);
    }
}
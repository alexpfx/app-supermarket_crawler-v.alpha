package br.com.alexpfx.supermarket.webcrawler.crawler.apibridge;

import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.parse.JSoupParseAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.visitor.HtmlUnitVisitorAPI;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
        Document p = crawlerAPI.parse(visit);
        p.setBaseUri("http://www.angeloni.com.br/");

        Elements elements = p.select("ul.lstProd li");
        for (Element element : elements) {
            Elements e = element.select("span.descr");
            System.out.println(e.text());

            Elements select = element.select("span.cod");
            System.out.println(select);

            String text = p.select("span.descr a").attr("abs:href");
            System.out.println(text);


        }

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
package br.com.alexpfx.supermarket.webcrawler.crawler.apibridge;

import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.parse.JSoupParseAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.visitor.HtmlUnitVisitorAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.visitor.JsoupVisitorAPI;
import com.google.common.base.CharMatcher;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.After;
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

    }



    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testVisit_Jsoup() throws Exception {
        crawlerAPI = getCrawlerAPIJsoup();
        String v = crawlerAPI.visit(
                "https://www.mercadoribeirao.com.br/produtos.php?id_sub=368&argentino&pageNum=VER-TUDO");


        assertNotNull(v);

    }

    private CrawlerAPIImpl getCrawlerAPIJsoup() {
        return new CrawlerAPIImpl(new JsoupVisitorAPI(), new JSoupParseAPI());
    }


    @Test
    public void testParse() throws Exception {
        crawlerAPI = getCrawlerAPIJsoup();
        String d = crawlerAPI.visit(
                "https://www.mercadoribeirao.com.br/produtos.php?id_sub=368&argentino&pageNum=VER-TUDO");
        Document document = crawlerAPI.parse(d);
        Element a = document.select("div.ratings-container").select("a").first();
        System.out.println(a.text().toLowerCase().replace("ref:", ""));

    }
}
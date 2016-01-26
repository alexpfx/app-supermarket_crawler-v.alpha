package br.com.alexpfx.supermarket.webcrawler.crawler;

import br.com.alexpfx.supermarket.webcrawler.crawler.api.JsopHandler;
import com.jaunt.*;
import com.jaunt.Document;
import com.jaunt.Element;
import com.jaunt.Elements;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by alexandre on 17/01/2016.
 */
public class UrlsCollectorTest {
    UserAgent userAgent;

    @Before
    public void setUp() throws Exception {
        userAgent = createUserAgent();

    }

    public UserAgent createUserAgent() {
        UserAgent u = new UserAgent();
        u.settings.charset = "ISO8859_1";
        return u;
    }


    @Test
    public void jsopTest (){
        org.jsoup.nodes.Document doc = JsopHandler.visit("https://www.mercadoribeirao.com.br/produtos.php?id_sub=167&pageNum=VER-TUDO&limpeza-da-cozinha");
        org.jsoup.select.Elements itemList = doc.select("div.item-meta-container");
        for (org.jsoup.nodes.Element e:itemList){
            org.jsoup.nodes.Element s = e.select("h3.item-name").select("a").first();
            System.out.println(s.attr("abs:href"));
        }

    }

    @Test
    public void jauntTest() throws NotFound {
        try {
            Document doc = userAgent.visit("https://www.mercadoribeirao.com.br/produtos.php?id_sub=388&micoses/calos/verrugas");
            Elements submenu = doc.findEach("<a class=new_sub_menu>");
            Elements links = submenu.findEach("<a>");
            for (Element e : links) {
                String href = e.getAt("href");
                System.out.println(href);
            }
        } catch (ResponseException e) {
            e.printStackTrace();
        }
    }
}
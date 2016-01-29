package br.com.alexpfx.supermarket.webcrawler.crawler;

import br.com.alexpfx.supermarket.webcrawler.crawler.api.JsopHandler;
import br.com.alexpfx.supermarket.webcrawler.factory.UserAgentFactory;
import com.crawljax.browser.EmbeddedBrowser;
import com.crawljax.core.CrawlSession;
import com.crawljax.core.CrawlerContext;
import com.crawljax.core.CrawljaxRunner;
import com.crawljax.core.configuration.BrowserConfiguration;
import com.crawljax.core.configuration.CrawljaxConfiguration;
import com.crawljax.core.plugin.OnNewStatePlugin;
import com.crawljax.core.state.StateVertex;
import com.jaunt.*;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

/**
 * Created by alexandre on 17/01/2016.
 */
public class CrawlersApiTest {
    public static final String URL = "http://www.angeloni.com.br/super/index?grupo=15022";
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


//    @Test
    public void jsopTest () throws IOException, ResponseException {
        org.jsoup.nodes.Document doc = JsopHandler.visit("https://www.mercadoribeirao.com.br/produtos.php?id_sub=167&pageNum=VER-TUDO&limpeza-da-cozinha");
        org.jsoup.select.Elements itemList = doc.select("div.item-meta-container");
        for (org.jsoup.nodes.Element e:itemList){
            org.jsoup.nodes.Element s = e.select("h3.item-name").select("a").first();
            //System.out.println(s.attr("abs:href"));

        }


        org.jsoup.nodes.Document document = JsopHandler.visit(URL);
        UserAgentFactory userAgentFactory = new UserAgentFactory();
        UserAgent userAgent = userAgentFactory.createUserAgent();
    }

    @Test
    public void crawljaxTest (){
        BrowserConfiguration browserconfig = new BrowserConfiguration(EmbeddedBrowser.BrowserType.CHROME);

        CrawljaxConfiguration config = CrawljaxConfiguration.builderFor(URL).setBrowserConfig(browserconfig).addPlugin(new OnNewStatePlugin() {
            @Override
            public void onNewState(CrawlerContext crawlerContext, StateVertex stateVertex) {
                System.out.println(crawlerContext.getBrowser().getStrippedDom());
            }
        }).build();


        CrawljaxRunner runner = new CrawljaxRunner(config);
        runner.call();




    }


    @Test
    public void webDriverTest() throws Exception{
        FirefoxDriver driver = new FirefoxDriver();

    }

    @Test
    public void crawler4jTest (){


    }

//    @Test
    public void jauntTest() throws NotFound, ResponseException {
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


        Document visit = userAgent.visit("http://www.angeloni.com.br/super/index?grupo=15022");


        boolean lstProd1 = visit.outerHTML().contains("lstProd");
        Elements lstProd = visit.findEach("<ul class='lstProd '");
        System.out.println(lstProd.size());


    }
}
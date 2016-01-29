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
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.jaunt.*;
import com.jaunt.Document;
import com.jaunt.Element;
import com.jaunt.Elements;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
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
    public void htmlUnitTest () throws IOException, ResponseException {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.waitForBackgroundJavaScript(10000);
        HtmlPage page = webClient.getPage(URL);


        String contentAsString = page.getWebResponse().getContentAsString();
        String s = page.asXml();
        System.out.println(s);

        org.jsoup.nodes.Document parse = Jsoup.parse(s);
        org.jsoup.select.Elements elementsByClass = parse.select("ul.lstProd ");
        System.out.println(elementsByClass);

        UserAgent userAgent = new UserAgentFactory().createUserAgent();
        Document document = userAgent.openContent(s);
        System.out.println(contentAsString);
        Elements each = document.findEach("<ul class='lstProd '>");
        for (Element element : each) {
            System.out.println(element);
        }



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
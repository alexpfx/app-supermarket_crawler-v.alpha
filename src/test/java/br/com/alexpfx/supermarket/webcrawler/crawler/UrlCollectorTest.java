package br.com.alexpfx.supermarket.webcrawler.crawler;

import br.com.alexpfx.supermarket.webcrawler.factory.UserAgentFactory;
import com.jaunt.Document;
import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.NotFound;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by alexandre on 17/01/2016.
 */
public class UrlCollectorTest {

    private UrlCollector collector;
    private UserAgentFactory userAgentFactory;

    @Before
    public void setUp() throws Exception {
        userAgentFactory = new UserAgentFactory();
    }


    private CollectorRule getVisitorRuleGoogle() {
        return doc -> {
            List<String> list = new ArrayList<String>();
            Elements elements = doc.findEvery("<h3 class=r>").findEvery("<a>");
            elements.forEach(element -> {
                try {
                    String href = element.getAt("href");
                    list.add(href);
                } catch (NotFound notFound) {
                    notFound.printStackTrace();
                }
            });
            return list;
        };
    }

    private CollectorRule getVisitorRuleAngeloni() {
        return new CollectorRule() {
            @Override
            public List<String> evaluate(Document doc) {
                List<String> list = new ArrayList<String>();
                Elements elements = doc.findEach("<a class='lnkTp01 '>");
                Element lnkTp02 = doc.findEach("<a class='lnkTp02 '>");
                elements.addChildren(0, lnkTp02.getChildNodes());
                elements.findEach("<a>").forEach(element -> {
                    String href = null;
                    try {
                        href = element.getAt("href");
                        list.add(href);
                    } catch (NotFound notFound) {
                        notFound.printStackTrace();
                    }
                });
                return list;
            }
        };
    }


    @After
    public void tearDown() throws Exception {

    }

    //    @Test
    public void testCollectGoogle() throws Exception {
        userAgentFactory = new UserAgentFactory();
        collector = new UrlCollector(getVisitorRuleGoogle(), Collections.singletonList("https://www.google.com.br/search?q=test&oq=test&aqs=chrome..69i57j69i60l3j69i59l2.7151j0j8&sourceid=chrome&es_sm=93&ie=UTF-8"));
        List<String> collect = collector.collect();
    }

    @Test
    public void testCollectAngeloni() throws Exception {
        userAgentFactory = new UserAgentFactory();
        collector = new UrlCollector(getVisitorRuleAngeloni(), Collections.singletonList("http://www.angeloni.com.br/super/index"));
        List<String> collect = collector.collect();
    }
}
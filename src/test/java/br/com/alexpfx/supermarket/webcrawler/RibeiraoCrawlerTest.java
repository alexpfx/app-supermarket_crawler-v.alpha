package br.com.alexpfx.supermarket.webcrawler;

import br.com.alexpfx.supermarket.webcrawler.crawler.impl.RibeiraoCrawler;
import br.com.alexpfx.supermarket.webcrawler.factory.UserAgentFactory;
import br.com.alexpfx.supermarket.webcrawler.listeners.impl.MercadoRibeiraoProductExtractListener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.function.Predicate;

/**
 * Created by alexandre on 01/01/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml"})
public class RibeiraoCrawlerTest {


    @Before
    public void setUp() throws Exception {
        RibeiraoCrawler crawler = new RibeiraoCrawler(new UserAgentFactory());
        crawler.setListener(new MercadoRibeiraoProductExtractListener());
        crawler.crawl();
    }

    @Test
    public void test() {
        String n = "12333451";
        int count = n.chars().map(p -> {
            return Character.getNumericValue(p);
        }).sum();

        spliter("testeeee", x -> (x % 2 == 0));

    }

    @After
    public void tearDown() throws Exception {

    }

    public String[] spliter(String rcv, Predicate<Integer> p) {
        String[] ss = new String[2];
        StringBuilder sb0 = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();

        char[] chars = rcv.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (p.test(i)) {
                sb0.append(chars[i]);
            } else {
                sb1.append(chars[i]);
            }
        }
        ss[0] = sb0.toString();
        ss[1] = sb1.toString();
        return ss;
    }
}

@FunctionalInterface
interface IndexSpliter {
    public String[] aplicar(String inteira);
}
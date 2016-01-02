package br.com.alexpfx.supermarket.crawler.jaunt;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by alexandre on 01/01/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml"})
public class RibeiraoCrawlerTest {
    CrawlerRunner crawlerRunner;


    @Before
    public void setUp() throws Exception {
        RibeiraoCrawler crawler = new RibeiraoCrawler(new UserAgentFactory());
        crawler.setProductExtractedListener(new MercadoRibeiraoProductExtractListener());
        crawlerRunner = new CrawlerRunner(crawler);
    }

    @Test
    public void test (){
        Thread t = new Thread(crawlerRunner);
        t.setDaemon(false);
        t.start();
        while (t.isAlive()){

        }

    }

    @After
    public void tearDown() throws Exception {
        crawlerRunner = null;
    }
}
package br.com.alexpfx.supermarket.batch.configuration;

import br.com.alexpfx.supermarket.batch.reader.ProductList;
import br.com.alexpfx.supermarket.bo.ProductBo;
import br.com.alexpfx.supermarket.bo.impl.ProductBoImpl;
import br.com.alexpfx.supermarket.dao.ProductDao;
import br.com.alexpfx.supermarket.dao.impl.ProductDaoImpl;
import br.com.alexpfx.supermarket.webcrawler.crawler.Crawler;
import br.com.alexpfx.supermarket.webcrawler.crawler.SupermarketCrawler;
import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.CrawlerAPIImpl;
import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.parse.JSoupParseAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.visitor.HtmlUnitVisitorAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.visitor.JsoupVisitorAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.collector.ItemsCollector;
import br.com.alexpfx.supermarket.webcrawler.crawler.collector.UrlsCollector;
import br.com.alexpfx.supermarket.webcrawler.crawler.collector.rules.AngeloniExtractionRules;
import br.com.alexpfx.supermarket.webcrawler.crawler.collector.rules.AngeloniVisitorRule;
import br.com.alexpfx.supermarket.webcrawler.crawler.collector.rules.RibeiraoExtractionRules;
import br.com.alexpfx.supermarket.webcrawler.crawler.collector.rules.RibeiraoVisitorRule;
import br.com.alexpfx.supermarket.webcrawler.listeners.CrawlerListener;
import br.com.alexpfx.supermarket.webcrawler.listeners.impl.RibeiraoListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * Created by alexandre on 13/01/2016.
 */
@Configuration
@EnableBatchProcessing
public class CommonBeansConfig {

    @Bean
    @Qualifier(value = "ribeiraoCrawler")
    public Crawler ribeiraoCrawler() {
        return new SupermarketCrawler(new UrlsCollector(
                new RibeiraoVisitorRule(new CrawlerAPIImpl(new JsoupVisitorAPI(), new JSoupParseAPI())),
                new CrawlerAPIImpl(new JsoupVisitorAPI(), new JSoupParseAPI())),
                                      new ItemsCollector(new RibeiraoExtractionRules(),
                                                         new CrawlerAPIImpl(new JsoupVisitorAPI(),
                                                                            new JSoupParseAPI())),
                                      Collections.singletonList("https://www.mercadoribeirao.com.br/"));
    }

    @Bean
    @Qualifier(value = "angeloniCrawler")
    public Crawler angeloniCrawler() {
        return new SupermarketCrawler(new UrlsCollector(
                new AngeloniVisitorRule(new CrawlerAPIImpl<>(new JsoupVisitorAPI(), new JSoupParseAPI())),
                new CrawlerAPIImpl(new HtmlUnitVisitorAPI(),
                                   new JSoupParseAPI())),
                                      new ItemsCollector(new AngeloniExtractionRules(),
                                                         new CrawlerAPIImpl(new HtmlUnitVisitorAPI(),
                                                                            new JSoupParseAPI())),
                                      Collections.singletonList("http://www.angeloni.com.br/super/index"));
    }

    @Bean
    public ProductBo productBo() {
        return new ProductBoImpl();
    }

    @Bean
    public ProductDao productDao() {
        return new ProductDaoImpl();
    }


    @Bean
    public CrawlerListener listener() {
        CrawlerListener listener = new RibeiraoListener();
        return listener;
    }

    @Bean
    public ProductList getProductList() {
        return new ProductList();
    }


}

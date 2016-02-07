package br.com.alexpfx.supermarket.batch.configuration;

import br.com.alexpfx.supermarket.batch.reader.ProductList;
import br.com.alexpfx.supermarket.bo.ProductBo;
import br.com.alexpfx.supermarket.bo.impl.ProductBoImpl;
import br.com.alexpfx.supermarket.dao.ProductDao;
import br.com.alexpfx.supermarket.dao.impl.ProductDaoImpl;
import br.com.alexpfx.supermarket.webcrawler.crawler.Crawler;
import br.com.alexpfx.supermarket.webcrawler.crawler.SupermarketCrawler;
import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.CrawlerAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.CrawlerAPIImpl;
import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.parse.JSoupParseAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.visitor.HtmlUnitVisitorAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.visitor.JsoupVisitorAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.collector.ExtractionRules;
import br.com.alexpfx.supermarket.webcrawler.crawler.collector.ItemsCollector;
import br.com.alexpfx.supermarket.webcrawler.crawler.collector.UrlsCollector;
import br.com.alexpfx.supermarket.webcrawler.crawler.collector.rules.AngeloniExtractionRules;
import br.com.alexpfx.supermarket.webcrawler.crawler.collector.rules.AngeloniVisitorRule;
import br.com.alexpfx.supermarket.webcrawler.crawler.collector.rules.RibeiraoExtractionRules;
import br.com.alexpfx.supermarket.webcrawler.crawler.collector.rules.RibeiraoVisitorRule;
import br.com.alexpfx.supermarket.webcrawler.listeners.CrawlerListener;
import br.com.alexpfx.supermarket.webcrawler.listeners.impl.RibeiraoListener;
import br.com.alexpfx.supermarket.webcrawler.to.TransferObject;
import org.jsoup.nodes.Document;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by alexandre on 13/01/2016.
 */
@Configuration
@EnableBatchProcessing
public class CommonBeansConfig {

    @Bean
    @Qualifier(value = "ribeiraoCrawler")
    public Crawler ribeiraoCrawler() {


        CrawlerAPI<Document> crawlerApi = new CrawlerAPIImpl<>(new JsoupVisitorAPI(), new JSoupParseAPI());

        ExtractionRules urlsCollectorExtractionRulles = new RibeiraoVisitorRule(crawlerApi);
        UrlsCollector urlsCollector = new UrlsCollector(crawlerApi, urlsCollectorExtractionRulles);


        ExtractionRules<TransferObject> itemsCollectorExtractionRules = new RibeiraoExtractionRules(crawlerApi);
        ItemsCollector itemsCollector = new ItemsCollector(crawlerApi, itemsCollectorExtractionRules);
        return new SupermarketCrawler(urlsCollector, itemsCollector,
                                      "https://www.mercadoribeirao.com.br/produto.php?id_prod=35176&ervilha-partida-yoki-500g/");
    }

    @Bean
    @Qualifier(value = "angeloniCrawler")
    public Crawler angeloniCrawler() {
        //extract urls
        CrawlerAPIImpl<Document> jsoupCrawlerAPI = new CrawlerAPIImpl<>(new JsoupVisitorAPI(), new JSoupParseAPI());
        AngeloniVisitorRule angeloniVisitorRule = new AngeloniVisitorRule(jsoupCrawlerAPI);


        AngeloniExtractionRules angeloniProductExtractRules = new AngeloniExtractionRules(jsoupCrawlerAPI);

        CrawlerAPIImpl htmlUnitVisitorApi = new CrawlerAPIImpl(new HtmlUnitVisitorAPI(),
                                                               new JSoupParseAPI());

        UrlsCollector urlsCollector = new UrlsCollector(jsoupCrawlerAPI, angeloniVisitorRule);
        ItemsCollector itemsCollector = new ItemsCollector(htmlUnitVisitorApi, angeloniProductExtractRules);

        return new SupermarketCrawler(urlsCollector, itemsCollector,
                                      "http://www.angeloni.com.br/super/index");
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

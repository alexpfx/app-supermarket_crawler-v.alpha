package br.com.alexpfx.supermarket.batch.configuration.ribeirao;

import br.com.alexpfx.supermarket.webcrawler.crawler.Crawler;
import br.com.alexpfx.supermarket.webcrawler.crawler.SupermarketCrawler;
import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.CrawlerAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.CrawlerAPIImpl;
import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.parse.JSoupParseAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.visitor.JsoupVisitorAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.collector.ExtractionRules;
import br.com.alexpfx.supermarket.webcrawler.crawler.collector.ItemsCollector;
import br.com.alexpfx.supermarket.webcrawler.crawler.collector.UrlsCollector;
import br.com.alexpfx.supermarket.webcrawler.crawler.collector.rules.RibeiraoExtractionRules;
import br.com.alexpfx.supermarket.webcrawler.crawler.collector.rules.RibeiraoVisitorRule;
import br.com.alexpfx.supermarket.webcrawler.to.TransferObject;
import org.jsoup.nodes.Document;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by alexandre on 07/02/2016.
 */
@Configuration
@EnableBatchProcessing
public class RibeiraoBeans {

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


}

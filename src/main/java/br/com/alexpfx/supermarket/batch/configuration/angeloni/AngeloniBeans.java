package br.com.alexpfx.supermarket.batch.configuration.angeloni;

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
public class AngeloniBeans {

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

}

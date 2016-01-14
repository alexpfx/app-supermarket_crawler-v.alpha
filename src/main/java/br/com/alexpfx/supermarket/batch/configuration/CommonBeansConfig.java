package br.com.alexpfx.supermarket.batch.configuration;

import br.com.alexpfx.supermarket.batch.reader.ProductList;
import br.com.alexpfx.supermarket.bo.ProductBo;
import br.com.alexpfx.supermarket.bo.impl.ProductBoImpl;
import br.com.alexpfx.supermarket.dao.ProductDao;
import br.com.alexpfx.supermarket.dao.impl.ProductDaoImpl;
import br.com.alexpfx.supermarket.webcrawler.crawler.Crawler;
import br.com.alexpfx.supermarket.webcrawler.crawler.impl.AngeloniCrawler;
import br.com.alexpfx.supermarket.webcrawler.crawler.impl.RibeiraoCrawler;
import br.com.alexpfx.supermarket.webcrawler.factory.UserAgentFactory;
import br.com.alexpfx.supermarket.webcrawler.listeners.CrawlerListener;
import br.com.alexpfx.supermarket.webcrawler.listeners.impl.RibeiraoListener;
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
    public Crawler crawler() {
        return new RibeiraoCrawler(new UserAgentFactory());
    }

    @Bean
    @Qualifier(value = "angeloniCrawler")
    public Crawler angeloniCrawler (){
        return new AngeloniCrawler(new UserAgentFactory());
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

package br.com.alexpfx.supermarket.batch.configuration.product;

import br.com.alexpfx.supermarket.batch.reader.ProductList;
import br.com.alexpfx.supermarket.bo.ProductBo;
import br.com.alexpfx.supermarket.bo.impl.ProductBoImpl;
import br.com.alexpfx.supermarket.dao.BaseDao;
import br.com.alexpfx.supermarket.dao.impl.ProductDaoImpl;
import br.com.alexpfx.supermarket.webcrawler.listeners.CrawlerListener;
import br.com.alexpfx.supermarket.webcrawler.listeners.impl.ProductListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by alexandre on 07/02/2016.
 */
@Configuration
@EnableBatchProcessing
public class ProductBeans {
    @Bean
    public ProductBo productBo() {
        return new ProductBoImpl();
    }

    @Bean
    public BaseDao productDao() {
        return new ProductDaoImpl();
    }


    @Bean
    public ProductList getProductList() {
        return new ProductList();
    }

    @Bean
    public CrawlerListener listener() {
        CrawlerListener listener = new ProductListener();
        return listener;
    }


}

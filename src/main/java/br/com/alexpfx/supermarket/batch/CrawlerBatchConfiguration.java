package br.com.alexpfx.supermarket.batch;

import br.com.alexpfx.supermarket.batch.reader.ProductItemReader;
import br.com.alexpfx.supermarket.batch.reader.ProductList;
import br.com.alexpfx.supermarket.batch.tasklet.StartCrawlerTasklet;
import br.com.alexpfx.supermarket.bo.ProductBo;
import br.com.alexpfx.supermarket.bo.impl.ProductBoImpl;
import br.com.alexpfx.supermarket.dao.ProductDao;
import br.com.alexpfx.supermarket.dao.impl.ProductDaoImpl;
import br.com.alexpfx.supermarket.webcrawler.crawler.Crawler;
import br.com.alexpfx.supermarket.webcrawler.crawler.impl.RibeiraoCrawler;
import br.com.alexpfx.supermarket.webcrawler.factory.UserAgentFactory;
import br.com.alexpfx.supermarket.webcrawler.listeners.CrawlerListener;
import br.com.alexpfx.supermarket.webcrawler.listeners.ProductExtractedListener;
import br.com.alexpfx.supermarket.webcrawler.listeners.impl.RibeiraoListener;
import br.com.alexpfx.supermarket.domain.Product;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.List;

/**
 * Created by alexandre on 03/01/2016.
 */
@Configuration
@EnableBatchProcessing
public class CrawlerBatchConfiguration {


    @Autowired
    private StepBuilderFactory steps;

    @Bean
    public Job job(JobBuilderFactory jobs) {
        return jobs.get("myJob").start(step0()).next(step1()).build();
    }

    protected Step step0() {
        return steps.get("setupCrawlerStep").tasklet(tasklet()).build();
    }

    @Bean
    protected Step step1() {
        return steps.get("processProductStep")
                .<Product, Product>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    protected Tasklet tasklet() {
        return new StartCrawlerTasklet();
    }


    @Bean
    protected ProductList getProductList() {
        return new ProductList();
    }

    @Bean
    public CrawlerListener listener() {
        ProductExtractedListener listener = new RibeiraoListener();
        return listener;
    }


    @Bean
    public ItemProcessor<Product, Product> processor() {
        return new ItemProcessor<Product, Product>() {
            @Override
            public Product process(Product product) throws Exception {
                return product;
            }
        };
    }

    @Bean
    public ItemReader<Product> reader() {
        return new ProductItemReader();
    }

    @Bean
    public ItemWriter<Product> writer() {
        return new ItemWriter<Product>() {
            @Override
            public void write(List<? extends Product> list) throws Exception {
                list.forEach(p -> System.out.println(p));
            }
        };
    }

    @Bean
    public Crawler crawler (){
        return new RibeiraoCrawler(new UserAgentFactory());
    }

    @Bean
    public ProductBo productBo (){
        return new ProductBoImpl();
    }

    @Bean
    public ProductDao productDao (){
        return new ProductDaoImpl();
    }

}

package br.com.alexpfx.supermarket.crawler.batch;

import br.com.alexpfx.supermarket.crawler.batch.reader.ProductItemReader;
import br.com.alexpfx.supermarket.crawler.batch.reader.ProductList;
import br.com.alexpfx.supermarket.crawler.jaunt.ProductExtractedListener;
import br.com.alexpfx.supermarket.crawler.model.domain.Product;
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

    @Bean
    protected Step step1() {
        return steps.get("processProductStep")
                .<Product, Product>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    protected Step step0() {
        return steps.get("setupCrawlerStep").tasklet(tasklet()).build();
    }

    @Bean
    private Tasklet tasklet() {
        return new InitializeCrawlerTasklet();
    }


    @Bean
    public ProductList getProductList() {
        return new ProductList();
    }

    @Bean
    @Scope("job")
    public ProductExtractedListener productExtractedListener() {
        ProductExtractedListener listener = new RibeiraoListener();
        return listener;
    }


    public ItemProcessor<Product, Product> processor() {
        return new ItemProcessor<Product, Product>() {
            @Override
            public Product process(Product product) throws Exception {
                return new Product();
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


}

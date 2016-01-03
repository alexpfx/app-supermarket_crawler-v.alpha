package br.com.alexpfx.supermarket.crawler.controller;

import br.com.alexpfx.supermarket.crawler.model.domain.Product;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

/**
 * Created by alexandre on 03/01/2016.
 */
@Configuration
@EnableBatchProcessing
public class CrawlerAppConfig {
    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    public Job job (@Qualifier("extractProductsStep") Step extractProductsStep){
        return jobs.get("myJob").start(extractProductsStep).build();
    }

    protected Step extractProductsStep (ItemReader<Product> reader, ItemProcessor<Product, Product> processor, ItemWriter<Product> writer){
        //return steps.get("extractProductsStep").<Product, Product> chunk(10);
        return null;
    }

    public static void main(String[] args) {

    }
}

package br.com.alexpfx.supermarket.batch.configuration;

import br.com.alexpfx.supermarket.batch.processor.ProductProcessor;
import br.com.alexpfx.supermarket.batch.reader.ProductItemReader;
import br.com.alexpfx.supermarket.batch.tasklet.StartCrawlerTasklet;
import br.com.alexpfx.supermarket.batch.writer.HibernateProductsItemWriter;
import br.com.alexpfx.supermarket.domain.Product;
import br.com.alexpfx.supermarket.webcrawler.to.TransferObject;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.AbstractJob;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by alexandre on 03/01/2016.
 */
@Configuration
@EnableBatchProcessing
@EnableJpaRepositories(basePackages = {"br.com.alexpfx.supermarket.domain"})
public class CrawlerJobConfiguration {


    @Autowired
    private InfrastructureConfiguration infrastructureConfiguration;

    @Autowired
    private StepBuilderFactory steps;
    @Autowired
    Environment environment;


    @Bean
    public Job job(JobBuilderFactory jobs) {
        Job theJob = jobs.get("job").start(crawlerStep()).next(processProductStep()).build();
        ((AbstractJob) theJob).setRestartable(true);
        return theJob;

    }

    private Step crawlerStep() {
        return steps.get("crawlerStep").tasklet(crawlerTasklet()).build();
    }

    private Step processProductStep() {
        return steps.get("processProductStep")
                .<TransferObject, Product>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    private Tasklet crawlerTasklet() {
        return new StartCrawlerTasklet();
    }

    private ItemProcessor<TransferObject, Product> processor() {
        return new ProductProcessor();
    }

    private ItemReader<TransferObject> reader() {
        return new ProductItemReader();
    }

    private ItemWriter<Product> writer() {
        return new HibernateProductsItemWriter();
    }


}

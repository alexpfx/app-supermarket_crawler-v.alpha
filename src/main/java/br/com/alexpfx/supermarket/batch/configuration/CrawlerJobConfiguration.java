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
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Created by alexandre on 03/01/2016.
 */
@Configuration
@EnableBatchProcessing
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

    @Bean
    public Step crawlerStep() {
        TaskletStep crawlerStep = steps.get("crawlerStep").tasklet(crawlerTasklet()).build();
        crawlerStep.setAllowStartIfComplete(true);
        return crawlerStep;
    }

    @Bean
    public Step processProductStep() {
        TaskletStep processProductStep = steps.get("processProductStep")
                .<TransferObject, Product>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
        processProductStep.setAllowStartIfComplete(true);
        return processProductStep;
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

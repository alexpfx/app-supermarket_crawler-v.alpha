package br.com.alexpfx.supermarket.batch.configuration.ribeirao;

import br.com.alexpfx.supermarket.batch.configuration.infracstructure.InfrastructureConfig;
import br.com.alexpfx.supermarket.batch.processor.ProductProcessor;
import br.com.alexpfx.supermarket.batch.reader.ProductItemReader;
import br.com.alexpfx.supermarket.batch.tasklet.StartCrawlerTasklet;
import br.com.alexpfx.supermarket.batch.writer.HibernateProductsItemWriter;
import br.com.alexpfx.supermarket.domain.Product;
import br.com.alexpfx.supermarket.webcrawler.crawler.Crawler;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Created by alexandre on 03/01/2016.
 */
@Configuration
@EnableBatchProcessing
public class RibeiraoJobConfig {


    @Autowired
    private InfrastructureConfig infrastructureConfig;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    Environment environment;

    @Autowired
    @Qualifier(value = "ribeiraoCrawler")
    Crawler ribeiraoCrawler;

    @Bean
    public Job job(JobBuilderFactory jobs) {
        Job theJob = jobs.get("ribeiraoJob").start(ribeiraoCrawlerStep()).next(
                ribeiraoTransformProductStep()).build();
        ((AbstractJob) theJob).setRestartable(true);
        return theJob;
    }


    @Bean
    public Step ribeiraoCrawlerStep() {
        TaskletStep crawlerStep = steps.get("ribeiraoCrawlerStep").tasklet(ribeiraoCrawlerTasklet()).build();
        crawlerStep.setAllowStartIfComplete(true);
        return crawlerStep;
    }

    @Bean
    public Tasklet ribeiraoCrawlerTasklet() {
        return new StartCrawlerTasklet(ribeiraoCrawler);
    }

    @Bean
    public Step ribeiraoTransformProductStep() {
        TaskletStep processProductStep = steps.get("ribeiraoTransformProductStep")
                .<TransferObject, Product>chunk(100)
                .reader(ribeiraoReader())
                .processor(ribeiraoProcessor())
                .writer(ribeiraoWriter())
                .build();
        processProductStep.setAllowStartIfComplete(true);
        return processProductStep;
    }


    @Bean
    public ItemReader<TransferObject> ribeiraoReader() {
        return new ProductItemReader();
    }

    @Bean
    public ItemProcessor<TransferObject, Product> ribeiraoProcessor() {
        return new ProductProcessor();
    }

    @Bean
    public ItemWriter<Product> ribeiraoWriter() {
        return new HibernateProductsItemWriter();
    }


}

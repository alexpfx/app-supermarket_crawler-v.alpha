package br.com.alexpfx.supermarket.batch.configuration.angeloni;

import br.com.alexpfx.supermarket.batch.configuration.infracstructure.InfrastructureConfig;
import br.com.alexpfx.supermarket.batch.processor.AngeloniProductProcessor;
import br.com.alexpfx.supermarket.batch.reader.ProductItemReader;
import br.com.alexpfx.supermarket.batch.tasklet.LoadProductListTasklet;
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
 * Created by alexandre on 07/02/2016.
 */
@Configuration
@EnableBatchProcessing
public class AngeloniJobConfig {

    @Autowired
    private InfrastructureConfig infrastructureConfig;

    @Autowired
    Environment environment;

    @Autowired
    @Qualifier(value = "angeloniCrawler")
    Crawler angeloniCrawler;

    @Autowired
    private StepBuilderFactory steps;


    @Bean
    public Job angeloniJob(JobBuilderFactory jobs) {
        AbstractJob angeloniJob = (AbstractJob) jobs.get("angeloniJob").start(loadProductListStep()).next(
                angeloniCrawlerStep()).next(
                angeloniTransformProductStep()).build();
        angeloniJob.setRestartable(true);
        return angeloniJob;
    }

    @Bean
    public Step loadProductListStep() {
        TaskletStep step = steps.get("loadProductListStep").tasklet(loadProductListTasklet()).build();
        step.setAllowStartIfComplete(true);
        return step;
    }

    @Bean
    public Tasklet loadProductListTasklet() {
        return new LoadProductListTasklet();
    }

    @Bean
    public Step angeloniCrawlerStep() {
        TaskletStep crawlerStep = steps.get("angeloniCrawlerStep").tasklet(angeloniCrawlerTasklet()).build();
        crawlerStep.setAllowStartIfComplete(true);
        return crawlerStep;
    }

    @Bean
    public Tasklet angeloniCrawlerTasklet() {
        return new StartCrawlerTasklet(angeloniCrawler);
    }


    @Bean
    public Step angeloniTransformProductStep() {
        TaskletStep step = steps.get("angeloniTransformProductStep").<TransferObject, Product>chunk(100)
                .reader(angeloniItemReader()).processor(angeloniProcessor()).writer(angeloniWriter()).build();
        step.setAllowStartIfComplete(true);
        return step;
    }


    @Bean
    public ItemReader<TransferObject> angeloniItemReader() {
        return new ProductItemReader();
    }

    @Bean
    public ItemProcessor<? super TransferObject, ? extends Product> angeloniProcessor() {
        return new AngeloniProductProcessor();
    }


    @Bean
    public ItemWriter<Product> angeloniWriter() {
        return new HibernateProductsItemWriter();
    }


}

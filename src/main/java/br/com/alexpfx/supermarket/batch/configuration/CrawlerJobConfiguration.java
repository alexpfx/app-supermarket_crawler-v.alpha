package br.com.alexpfx.supermarket.batch.configuration;

import br.com.alexpfx.supermarket.batch.processor.ProductProcessor;
import br.com.alexpfx.supermarket.batch.reader.ProductItemReader;
import br.com.alexpfx.supermarket.batch.reader.ProductList;
import br.com.alexpfx.supermarket.batch.tasklet.StartCrawlerTasklet;
import br.com.alexpfx.supermarket.batch.writer.HIbernateProductsItemWriter;
import br.com.alexpfx.supermarket.bo.ProductBo;
import br.com.alexpfx.supermarket.bo.impl.ProductBoImpl;
import br.com.alexpfx.supermarket.dao.ProductDao;
import br.com.alexpfx.supermarket.dao.impl.ProductDaoImpl;
import br.com.alexpfx.supermarket.domain.Product;
import br.com.alexpfx.supermarket.webcrawler.crawler.Crawler;
import br.com.alexpfx.supermarket.webcrawler.crawler.impl.RibeiraoCrawler;
import br.com.alexpfx.supermarket.webcrawler.factory.UserAgentFactory;
import br.com.alexpfx.supermarket.webcrawler.listeners.CrawlerListener;
import br.com.alexpfx.supermarket.webcrawler.listeners.impl.RibeiraoListener;
import br.com.alexpfx.supermarket.webcrawler.to.TransferObject;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.AbstractJob;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
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
        Job theJob = jobs.get("theJob").start(flow()).end().build();
        ((AbstractJob) theJob).setRestartable(true);
        return theJob;

    }


    protected Step crawlerStep() {
        return steps.get("crawlerStep").tasklet(crawlerTasklet()).build();
    }

    private Flow flow() {
        FlowBuilder<Flow> builder = new FlowBuilder<>("flow1");
        builder.start(crawlerStep())
                .next(processProductStep())
                .end();
        return builder.build();
    }


    @Bean
    protected Step processProductStep() {
        return steps.get("processProductStep")
                .<TransferObject, Product>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    protected Tasklet crawlerTasklet() {
        return new StartCrawlerTasklet();
    }


    @Bean
    protected ProductList getProductList() {
        return new ProductList();
    }

    @Bean
    public CrawlerListener listener() {
        String dbUrl = environment.getProperty("db.url");

        CrawlerListener listener = new RibeiraoListener();
        return listener;
    }

    @Bean
    public ItemProcessor<TransferObject, Product> processor() {
        return new ProductProcessor();
    }

    @Bean
    public ItemReader<TransferObject> reader() {
        return new ProductItemReader();
    }

    @Bean
    public ItemWriter<Product> writer() {
        return new HIbernateProductsItemWriter();
    }

    @Bean
    public Crawler crawler() {
        return new RibeiraoCrawler(new UserAgentFactory());
    }

    @Bean
    public ProductBo productBo() {
        return new ProductBoImpl();
    }

    @Bean
    public ProductDao productDao() {
        return new ProductDaoImpl();
    }


}

package br.com.alexpfx.supermarket.batch;

import br.com.alexpfx.supermarket.batch.reader.ProductItemReader;
import br.com.alexpfx.supermarket.batch.reader.ProductList;
import br.com.alexpfx.supermarket.batch.tasklet.StartCrawlerTasklet;
import br.com.alexpfx.supermarket.bo.ProductBo;
import br.com.alexpfx.supermarket.bo.impl.ProductBoImpl;
import br.com.alexpfx.supermarket.dao.ProductDao;
import br.com.alexpfx.supermarket.dao.impl.ProductDaoImpl;
import br.com.alexpfx.supermarket.domain.Product;
import br.com.alexpfx.supermarket.domain.ProductBuilder;
import br.com.alexpfx.supermarket.webcrawler.crawler.Crawler;
import br.com.alexpfx.supermarket.webcrawler.crawler.impl.RibeiraoCrawler;
import br.com.alexpfx.supermarket.webcrawler.factory.UserAgentFactory;
import br.com.alexpfx.supermarket.webcrawler.listeners.CrawlerListener;
import br.com.alexpfx.supermarket.webcrawler.listeners.impl.RibeiraoListener;
import br.com.alexpfx.supermarket.webcrawler.to.ProdutoSuperMercadoTO;
import br.com.alexpfx.supermarket.webcrawler.to.TransferObject;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        return jobs.get("myJob").start(flow()).end().build();
    }

    protected Step step0() {
        return steps.get("setupCrawlerStep").tasklet(tasklet()).build();
    }

    private Flow flow() {
        FlowBuilder<Flow> builder = new FlowBuilder<>("flow1");
        builder.start(step0())
                .next(step1())
                .end();
        return builder.build();
    }


    @Bean
    protected Step step1() {
        return steps.get("processProductStep")
                .<TransferObject, Product>chunk(10)
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
        CrawlerListener listener = new RibeiraoListener();
        return listener;
    }


    @Bean
    public ItemProcessor<TransferObject, Product> processor() {
        return transferObject -> {
            Product p = new ProductBuilder().create();
            ProdutoSuperMercadoTO to = (ProdutoSuperMercadoTO) transferObject;
            p.setDescription(to.getDescricao());
            p.setUrl(to.getUrl());
            return p;
        };
    }

    @Bean
    public ItemReader<TransferObject> reader() {
        return new ProductItemReader();
    }

    @Bean
    public ItemWriter<Product> writer() {
        return list -> list.forEach(p -> System.out.println(p));
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

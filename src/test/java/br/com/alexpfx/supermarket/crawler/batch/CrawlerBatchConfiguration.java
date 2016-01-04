package br.com.alexpfx.supermarket.crawler.batch;

import br.com.alexpfx.supermarket.crawler.batch.reader.ProductItemReader;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by alexandre on 03/01/2016.
 */
@Configuration
@EnableBatchProcessing
public class CrawlerBatchConfiguration {
    @Bean
    public Job job(JobBuilderFactory jobs, Step step) {
        return jobs.get("myJob").start(step).build();
    }

    @Bean
    protected Step step(StepBuilderFactory steps) {
        return steps.get("step")
                .<Product, Product>chunk(11)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    protected Step runCrawlerStep(StepBuilderFactory steps) {
        return steps.get("runCrawlerStep").tasklet(tasklet()).build();
    }

    private Tasklet tasklet() {
        return new RunCrawlerTasklet();
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

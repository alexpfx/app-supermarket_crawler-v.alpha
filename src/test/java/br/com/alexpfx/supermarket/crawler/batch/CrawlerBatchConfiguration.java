package br.com.alexpfx.supermarket.crawler.batch;

import br.com.alexpfx.supermarket.crawler.model.domain.Product;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
        return new ItemReader<Product>() {
            @Override
            public Product read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                return new Product();
            }
        };

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

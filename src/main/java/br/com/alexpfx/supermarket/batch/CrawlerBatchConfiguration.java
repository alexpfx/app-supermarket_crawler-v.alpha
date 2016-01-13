package br.com.alexpfx.supermarket.batch;

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
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by alexandre on 03/01/2016.
 */
@Configuration
@EnableBatchProcessing
@PropertySource("classpath:database.properties")
@EnableJpaRepositories(basePackages = {"br.com.alexpfx.supermarket.domain"})
public class CrawlerBatchConfiguration {


    @Value("${jdbc.url}")
    String url;

    @Value("${jdbc.driverClassName}")
    String driverClassName;

    @Value("jdbc.username")
    String username;

    @Value("jdbc.password")
    String password;

    @Autowired
    private StepBuilderFactory steps;
    @Autowired
    Environment environment;


    @Bean
    public Job job(JobBuilderFactory jobs) {
        return jobs.get("myJob").start(flow()).end().build();
    }

    protected Step step0() {
        return steps.get("setupCrawlerStep").tasklet(tasklet()).build();
    }

    private Flow flow() {
        System.out.println(driverClassName);
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
        String dbUrl = environment.getProperty("db.url");

        CrawlerListener listener = new RibeiraoListener();
        return listener;
    }


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);


        return ds;
    }

    @Bean
    public EntityManagerFactory  entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String []{"br.com.alexpfx.supermarket.domain"});

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalJpaProperties());
        em.afterPropertiesSet();
        return em.getObject();
    }

    private Properties additionalJpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("current_session_context_class", "thread");
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory());
        transactionManager.setDataSource(dataSource());
        return transactionManager;
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

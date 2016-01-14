package br.com.alexpfx.supermarket.batch.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by alexandre on 13/01/2016.
 */
public interface InfrastructureConfig {
    @Bean
    DataSource getDataSource();

    @Bean
    PlatformTransactionManager transactionManager();

    @Bean
    EntityManagerFactory entityManagerFactory();
}

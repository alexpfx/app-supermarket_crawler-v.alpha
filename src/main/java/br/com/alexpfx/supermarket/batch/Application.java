package br.com.alexpfx.supermarket.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by alexandre on 03/01/2016.
 */
@SpringBootApplication
public class Application {

    public static final String ANGELONI_JOB = "angeloniCrawlerJob";
    public static final String RIBEIRAO_JOB = "ribeiraoCrawlerJob";

    public static void main(String[] args) {
        System.setProperty("spring.batch.job.names", RIBEIRAO_JOB);
        SpringApplication.run(Application.class, args);

    }
}

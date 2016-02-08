package br.com.alexpfx.supermarket.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by alexandre on 03/01/2016.
 */
@SpringBootApplication
public class Application {

    public static final String ANGELONI_JOB = "angeloniJob";
    public static final String RIBEIRAO_JOB = "ribeiraoJob";

    public static void main(String[] args) {
        System.setProperty("spring.batch.job.names", ANGELONI_JOB);
        SpringApplication.run(Application.class, args);

    }
}

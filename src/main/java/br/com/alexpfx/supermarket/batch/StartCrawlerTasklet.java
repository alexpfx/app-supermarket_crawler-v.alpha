package br.com.alexpfx.supermarket.batch;

import br.com.alexpfx.supermarket.webcrawler.crawler.Crawler;
import br.com.alexpfx.supermarket.webcrawler.crawler.CrawlerRunner;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by alexandre on 04/01/2016.
 */
public class StartCrawlerTasklet implements Tasklet {
    @Autowired
    private Crawler crawler;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        CrawlerRunner crawlerRunner = new CrawlerRunner(crawler);
        Thread t = new Thread(crawlerRunner);

        t.start();
        return RepeatStatus.FINISHED;
    }
}

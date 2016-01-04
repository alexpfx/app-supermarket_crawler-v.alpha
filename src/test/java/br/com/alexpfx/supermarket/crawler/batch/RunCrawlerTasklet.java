package br.com.alexpfx.supermarket.crawler.batch;

import br.com.alexpfx.supermarket.crawler.jaunt.CrawlerRunner;
import br.com.alexpfx.supermarket.crawler.jaunt.RibeiraoCrawler;
import br.com.alexpfx.supermarket.crawler.jaunt.UserAgentFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * Created by alexandre on 04/01/2016.
 */
public class RunCrawlerTasklet implements Tasklet {
    CrawlerRunner crawlerRunner;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        crawlerRunner = new CrawlerRunner(new RibeiraoCrawler(new UserAgentFactory()));
        crawlerRunner.run();
        return null;
    }
}

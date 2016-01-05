package br.com.alexpfx.supermarket.crawler.batch;

import br.com.alexpfx.supermarket.crawler.jaunt.Crawler;
import br.com.alexpfx.supermarket.crawler.jaunt.CrawlerRunner;
import com.google.common.base.Preconditions;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by alexandre on 04/01/2016.
 */
public class CrawlerStarterTasklet implements Tasklet{


    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        return null;
    }

}

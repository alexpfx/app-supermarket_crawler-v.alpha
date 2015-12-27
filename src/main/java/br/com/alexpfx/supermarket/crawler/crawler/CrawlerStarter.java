package br.com.alexpfx.supermarket.crawler.crawler;

import br.com.alexpfx.supermarket.crawler.model.bo.ProductBo;
import br.com.alexpfx.supermarket.crawler.model.domain.Product;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by alexandre on 06/12/2015.
 */
public class CrawlerStarter {

    private CrawlerControllerConfig config;

    public void setConfig(CrawlerControllerConfig config) {
        this.config = config;
    }

    public CrawlerStarter(CrawlerControllerConfig config) {
        this.config = config;
    }

    public void start() {
        String storageFolder = config.getStorageFolder();
        int numberOfCralers = config.getNumberOfCrawlers();
        String[] seeds = config.getSeeds();
        Class<? extends Crawler> crawlerClass = config.getControllerClass();

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(storageFolder);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = null;
        try {
            controller = new CrawlController(config, pageFetcher, robotstxtServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String seed : seeds) {
            controller.addSeed(seed);
        }
        controller.start(crawlerClass, numberOfCralers);
    }
}

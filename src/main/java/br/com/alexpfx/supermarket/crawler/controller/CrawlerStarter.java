package br.com.alexpfx.supermarket.crawler.controller;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * Created by alexandre on 06/12/2015.
 */
public class CrawlerStarter {

    private ControllerConfig config;

    public CrawlerStarter(ControllerConfig config) {
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

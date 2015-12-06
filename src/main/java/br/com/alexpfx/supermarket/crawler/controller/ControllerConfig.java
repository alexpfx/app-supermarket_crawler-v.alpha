package br.com.alexpfx.supermarket.crawler.controller;

/**
 * Created by alexandre on 06/12/2015.
 */
public interface ControllerConfig {
    String getStorageFolder();

    int getNumberOfCrawlers();

    String[] getSeeds();

    Class<? extends Crawler> getControllerClass();
}

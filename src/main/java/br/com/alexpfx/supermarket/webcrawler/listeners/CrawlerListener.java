package br.com.alexpfx.supermarket.webcrawler.listeners;

import br.com.alexpfx.supermarket.webcrawler.to.TransferObject;

import java.util.logging.Logger;

/**
 * Created by alexandre on 05/01/2016.
 */
public interface CrawlerListener {


    void itemExtracted(TransferObject product);

    CrawlerListener EMPTY = new CrawlerListener() {
        private final Logger LOG = Logger.getAnonymousLogger();

        @Override
        public void itemExtracted(TransferObject product) {
            LOG.warning("CRAWLER LISTENER NOT SET");
        }
    };

}

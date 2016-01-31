package br.com.alexpfx.supermarket.webcrawler.crawler.collector;

import java.util.List;

/**
 * Created by alexandre on 17/01/2016.
 */
@FunctionalInterface
public interface ExtractionRules<T> {
    List<T> extract(String htmlCode);

    ExtractionRules<String> EMPTY = new ExtractionRules() {
        @Override
        public List<String> extract(String htmlCode) {
            throw new RuntimeException("ExtractionRules not setted");
        }
    };
}

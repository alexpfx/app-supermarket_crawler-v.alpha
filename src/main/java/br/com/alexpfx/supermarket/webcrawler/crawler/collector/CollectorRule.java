package br.com.alexpfx.supermarket.webcrawler.crawler.collector;

import java.util.List;

/**
 * Created by alexandre on 17/01/2016.
 */
@FunctionalInterface
public interface CollectorRule <T> {
    List<T> evaluate(String htmlCode);

    CollectorRule<String> EMPTY = new CollectorRule() {
        @Override
        public List<String> evaluate(String htmlCode) {
            throw new RuntimeException("CollectorRule not setted");
        }
    };
}

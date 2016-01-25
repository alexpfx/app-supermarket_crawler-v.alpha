package br.com.alexpfx.supermarket.webcrawler.crawler;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by alexandre on 17/01/2016.
 */
@FunctionalInterface
public interface CollectorRule <T> {
    List<T> evaluate(Document doc);

    CollectorRule<String> EMPTY = new CollectorRule() {
        @Override
        public List<String> evaluate(Document doc) {
            throw new RuntimeException("CollectorRule not setted");
        }
    };
}

package br.com.alexpfx.supermarket.webcrawler.crawler;

import com.jaunt.Document;

import java.util.List;

/**
 * Created by alexandre on 17/01/2016.
 */
@FunctionalInterface
public interface CollectorRule {
    List<String> evaluate(Document doc);

    CollectorRule EMPTY = new CollectorRule() {
        @Override
        public List<String> evaluate(Document doc) {
            throw new RuntimeException("CollectorRule not setted");
        }
    };
}

package br.com.alexpfx.supermarket.webcrawler.crawler;

import com.jaunt.Document;

import java.util.List;

/**
 * Created by alexandre on 17/01/2016.
 */
public interface VisitorRule {
    List<String> evaluate(Document doc);

    VisitorRule EMPTY = new VisitorRule() {
        @Override
        public List<String> evaluate(Document doc) {
            throw new RuntimeException("VisitorRule not setted");
        }
    };
}

package br.com.alexpfx.supermarket.webcrawler.crawler;

import com.jaunt.Document;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alexandre on 16/01/2016.
 */
public class UrlCollector implements Collector<String> {
    private UserAgent userAgent;

    private CollectorRule collectorRule;
    private List<String> startUrls;
    private CollectorListener<String> collectorListener = CollectorListener.EMPTY;

    public UrlCollector(UserAgent userAgent, CollectorRule collectorRule, List<String> startUrls) {
        this.userAgent = userAgent;
        this.collectorRule = collectorRule;
        this.startUrls = startUrls;
    }


    @Override
    public void setCollectorRule(CollectorRule<String> collectorRule) {
        this.collectorRule = collectorRule;
    }

    public List<String> collect() {
        return collect(startUrls, new ArrayList<>());
    }

    @Override
    public void setCollectorListener(CollectorListener<String> collectorListener) {
        this.collectorListener = collectorListener;
    }

    private List<String> collect(List<String> toVisit, List<String> lista) {
        if (toVisit.isEmpty()) {
            return lista;
        }

        List<String> l = new ArrayList<>();
        for (String url : toVisit) {
            System.out.println(url);
            List<String> evaluated = Collections.EMPTY_LIST;
            try {
                evaluated = evaluate(userAgent.visit(url));
            } catch (ResponseException e) {
                System.out.println(e);
            }
            evaluated = evaluated.stream().filter(p -> !(lista.contains(p))).collect(Collectors.toList());
            l.addAll(evaluated);
        }
        lista.addAll(l);
        return collect(l, lista);

    }

    private List<String> evaluate(Document doc) {
        return collectorRule.evaluate(doc);
    }

    public UserAgent getUserAgent() {
        return userAgent;
    }
}

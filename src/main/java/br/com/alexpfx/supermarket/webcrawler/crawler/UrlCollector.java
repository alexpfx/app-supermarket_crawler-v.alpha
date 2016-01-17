package br.com.alexpfx.supermarket.webcrawler.crawler;

import com.google.common.base.Preconditions;
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

    public UrlCollector(CollectorRule collectorRule, List<String> startUrls) {
        this.collectorRule = collectorRule;
        this.startUrls = startUrls;
    }


    @Override
    public void setCollectorRule(CollectorRule<String> collectorRule) {
        this.collectorRule = collectorRule;
    }

    @Override
    public void setCollectorListener(CollectorListener<String> collectorListener) {
        this.collectorListener = collectorListener;
    }

    @Override
    public void setUserAgent(UserAgent userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public List<String> collect() {
        Preconditions.checkNotNull(userAgent);
        return collect(startUrls, new ArrayList<>());
    }

    private List<String> collect(List<String> toVisit, List<String> lista) {
        List<String> subList = new ArrayList<>();
        for (String url : toVisit) {
            System.out.println(url);
            List<String> evaluated = Collections.EMPTY_LIST;
            try {
                evaluated = evaluate(userAgent.visit(url));
            } catch (ResponseException e) {
                //TODO: log
            }
            evaluated = evaluated.stream().filter(p -> !(lista.contains(p))).collect(Collectors.toList());
            evaluated.forEach(s -> {
                collectorListener.collected(s);
                subList.add(s);
            });
        }
        lista.addAll(subList);
        return toVisit.isEmpty() ? lista : collect(subList, lista);
    }

    private List<String> evaluate(Document doc) {
        return collectorRule.evaluate(doc);
    }

}

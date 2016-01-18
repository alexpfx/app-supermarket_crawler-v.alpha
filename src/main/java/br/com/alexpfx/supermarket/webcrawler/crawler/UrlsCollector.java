package br.com.alexpfx.supermarket.webcrawler.crawler;

import com.jaunt.ResponseException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alexandre on 16/01/2016.
 */
public class UrlsCollector extends AbstractCollector<String> {

    public UrlsCollector(CollectorRule collectorRule) {
        this.collectorRule = collectorRule;
    }

    @Override
    public List<String> doCollect(List<String> urls) {
        return doCollect(urls, new ArrayList<>());
    }

    private List<String> doCollect(List<String> toVisit, List<String> lista) {
        List<String> subList = new ArrayList<>();
        for (String url : toVisit) {
            System.out.println(url);
            List<String> evaluated = evaluate(url);
            evaluated = evaluated.stream().filter(p -> !(lista.contains(p))).collect(Collectors.toList());
            evaluated.forEach(s -> {
                collectorListener.collected(s);
                subList.add(s);
            });
        }
        lista.addAll(subList);
        return toVisit.isEmpty() ? lista : doCollect(subList, lista);
    }

    private List<String> evaluate(String url) {
        try {
            return collectorRule.evaluate(userAgent.visit(url));
        } catch (ResponseException e) {
            //LOG
            return Collections.EMPTY_LIST;
        }
    }

}

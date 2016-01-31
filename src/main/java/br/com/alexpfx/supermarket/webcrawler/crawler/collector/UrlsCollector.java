package br.com.alexpfx.supermarket.webcrawler.crawler.collector;

import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.CrawlerAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alexandre on 16/01/2016.
 */
public class UrlsCollector extends AbstractCollector<String> {

    public UrlsCollector(CollectorRule collectorRule, CrawlerAPI crawlerAPI) {
        super(crawlerAPI);
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
            subList.addAll(evaluated);
        }
        lista.addAll(subList);
        return toVisit.isEmpty() ? lista : doCollect(subList, lista);
    }

    private List<String> evaluate(String url) {
        return collectorRule.evaluate(crawlerAPI.visit(url));
    }

}

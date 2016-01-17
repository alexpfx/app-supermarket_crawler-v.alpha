package br.com.alexpfx.supermarket.webcrawler.crawler;

import com.jaunt.Document;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alexandre on 16/01/2016.
 */
public class Visitor {
    private UserAgent userAgent;

    private VisitorRule visitorRule;

    public Visitor(UserAgent userAgent) {
        this.userAgent = userAgent;
    }

    public List<String> collect(List<String> startUrls) throws ResponseException {
        return collect(startUrls, new ArrayList<>());
    }

    private List<String> collect(List<String> toVisit, List<String> lista) throws ResponseException {
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

    public void setVisitorRule(VisitorRule visitorRule) {
        this.visitorRule = visitorRule;
    }

    private List<String> evaluate(Document doc) {
        return visitorRule.evaluate(doc);
    }
}

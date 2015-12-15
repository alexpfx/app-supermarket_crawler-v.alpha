package br.com.alexpfx.supermarket.crawler.model.domain;

import com.google.common.base.Splitter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by alexandre on 13/12/2015.
 */
public class KeywordTokenizer {

    private String phrase;
    private int minimumLength;
    private Set<String> filtered;

    public KeywordTokenizer(String phrase, int minimumLength) {
        this(phrase, minimumLength, new TreeSet<>());
    }

    public KeywordTokenizer(String phrase, int minimumLength, Set<String> filtered) {
        this.phrase = phrase;
        this.minimumLength = minimumLength;
        this.filtered = filtered;
    }

    public Set<String> getTokens() {
        Set<String> r = new TreeSet<>();
        Iterable<String> splited = Splitter.on(" ").trimResults().omitEmptyStrings().split(phrase);
        splited.forEach(s -> {
            if ((s.length() >= minimumLength) && (!filtered.contains(s))){
                r.add(s);
            }
        });
        return r;
    }
}

package br.com.alexpfx.supermarket.crawler.model.domain;

import java.util.*;

/**
 * Created by alexandre on 09/12/2015.
 */
public class Keywords {

    public static final int MIN_WORD_SIZE = 4;
    private Set<String> wordList;
    private Set<String> filtered;

    private Keywords(String phrase) {
        wordList = new KeywordTokenizer(phrase, MIN_WORD_SIZE).getTokens();
    }

    private Keywords(String phrase, Set<String> filtered) {
        this (phrase);
        this.filtered = filtered;
    }

    @Override
    public String toString() {
        return String.format("%100s\t", wordList);
    }

    public boolean contains(String keyword) {
        return wordList.contains(keyword);
    }


    public Iterator<String> getIterator() {
        return wordList.iterator();
    }

}

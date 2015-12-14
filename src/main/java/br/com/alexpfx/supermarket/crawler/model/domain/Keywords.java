package br.com.alexpfx.supermarket.crawler.model.domain;

import java.util.*;

/**
 * Created by alexandre on 09/12/2015.
 */
public class Keywords {

    public static final int MIN_WORD_SIZE = 4;
    private Set<String> wordList;

    public Keywords(String phrase) {
        wordList = new KeywordTokenizer(phrase, MIN_WORD_SIZE).getTokens();
    }

    public boolean contains(String keyword) {
        return wordList.contains(keyword);
    }


    public Iterator<String> getIterator() {
        return wordList.iterator();
    }

}

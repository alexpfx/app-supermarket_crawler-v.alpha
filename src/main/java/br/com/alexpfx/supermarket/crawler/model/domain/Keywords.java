package br.com.alexpfx.supermarket.crawler.model.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by alexandre on 09/12/2015.
 */
public class Keywords {

    private List<String> wordList = new ArrayList<>();

    public void add(String keyword) {
        wordList.add(keyword);
    }

    public boolean contains(String keyword) {
        return wordList.contains(keyword);
    }


    public static Keywords ofPhrase(String phrase) {
        Keywords keywords = new Keywords();
        StringTokenizer tokenizer = new StringTokenizer(phrase, " ", false);
        while (tokenizer.hasMoreTokens()) {
            String t = tokenizer.nextToken();
            if (t.length() > 4 && !keywords.contains(t)) {
                keywords.add(t);
            }
        }
        return keywords;
    }

    public List<String> getWordList() {
        return wordList;
    }
}

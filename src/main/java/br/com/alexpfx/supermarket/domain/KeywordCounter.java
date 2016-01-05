package br.com.alexpfx.supermarket.domain;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collector;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

/**
 * Created by alexandre on 10/12/2015.
 */
public class KeywordCounter {

    private Map<String, Integer> wordMap = Collections.synchronizedMap(new LinkedHashMap<>());

    public synchronized void add(Keywords keywords) {
        Iterator<String> iterator = keywords.getIterator();
        while (iterator.hasNext()) {
            String w = iterator.next();
            add(w);
        }
    }

    public synchronized Integer add(String word) {
        Integer q = notNullOrZero(wordMap.get(word));
        wordMap.put(word, ++q);
        return q;
    }

    public int getWordCount(String word) {
        return notNullOrZero(wordMap.get(word));
    }

    public int getNumberOfKeyword() {
        return wordMap.size();
    }

    private Integer notNullOrZero(Integer q) {
        return (q == null) ? 0 : q;
    }


    public Map<String, Integer> top(int q) {
        Collector<Entry<String, Integer>, ?, LinkedHashMap<String, Integer>> collector = toMap(Entry::getKey, Entry::getValue, (x, y) -> {
                    throw new AssertionError();
                },
                LinkedHashMap::new);
        LinkedHashMap<String, Integer> collected = wordMap.entrySet().stream().sorted(comparingByValue(Comparator.reverseOrder())).collect(collector);
        return collected.entrySet().stream().limit(q).collect(collector);

    }


}

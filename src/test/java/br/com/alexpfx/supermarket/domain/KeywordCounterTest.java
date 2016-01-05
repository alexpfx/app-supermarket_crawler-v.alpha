package br.com.alexpfx.supermarket.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by alexandre on 10/12/2015.
 */
public class KeywordCounterTest {
    private KeywordCounter keywordCounter;

    @Before
    public void setup() {
        keywordCounter = new KeywordCounter();
    }

    @Test
    public void testGetWordCount() throws Exception {
        preencher();
        assertEquals(6, keywordCounter.getWordCount("viola"));
        assertEquals(0, keywordCounter.getWordCount("tomate"));
        assertEquals(8, keywordCounter.getWordCount("gato"));


    }

    private void preencher() {
        keywordCounter.add("viola");
        keywordCounter.add("caderno");
        keywordCounter.add("gato");
        keywordCounter.add("cachorro");
        keywordCounter.add("gato");
        keywordCounter.add("borracha");
        keywordCounter.add("viola");
        keywordCounter.add("coruja");
        keywordCounter.add("monitor");
        keywordCounter.add("mouse");
        keywordCounter.add("mouse");
        keywordCounter.add("viola");
        keywordCounter.add("terno");
        keywordCounter.add("terno");
        keywordCounter.add("mouse");
        keywordCounter.add("viola");
        keywordCounter.add("terno");
        keywordCounter.add("terno");
        keywordCounter.add("mouse");
        keywordCounter.add("viola");
        keywordCounter.add("terno");
        keywordCounter.add("terno");
        keywordCounter.add("mouse");
        keywordCounter.add("viola");
        keywordCounter.add("terno");
        keywordCounter.add("terno");
        keywordCounter.add("viagem");
        keywordCounter.add("mouse");
        keywordCounter.add("rua");
        keywordCounter.add("rua");
        keywordCounter.add("mouse");
        keywordCounter.add("rua");
        keywordCounter.add("rua");
        keywordCounter.add("mouse");
        keywordCounter.add("rua");
        keywordCounter.add("rua");
        keywordCounter.add("mouse");
        keywordCounter.add("rua");
        keywordCounter.add("rua");
        keywordCounter.add("rua");
        keywordCounter.add("madeira");
        keywordCounter.add("bolacha");
        keywordCounter.add("borracha");
        keywordCounter.add("tenis");
        keywordCounter.add("rua");
        keywordCounter.add("mouse");
        keywordCounter.add("gato");
        keywordCounter.add("gato");
        keywordCounter.add("gato");
        keywordCounter.add("gato");
        keywordCounter.add("gato");
        keywordCounter.add("gato");
    }

    @Test
    public void testTop() {
        String[] topKeys = new String[]{"mouse", "rua", "gato"};
        Integer[] topValues = new Integer[]{10, 10, 8};
        preencher();
        Map<String, Integer> top = keywordCounter.top(3);
        assertEquals(3, top.size());
        Iterator<Map.Entry<String, Integer>> it = top.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            Map.Entry<String, Integer> e = it.next();
            assertEquals(e.getKey(), topKeys[i]);
            assertEquals(e.getValue(), topValues[i++]);
        }
    }
}


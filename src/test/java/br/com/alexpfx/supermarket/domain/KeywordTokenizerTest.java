package br.com.alexpfx.supermarket.domain;

import org.junit.After;
import org.junit.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

/**
 * Created by alexandre on 13/12/2015.
 */
public class KeywordTokenizerTest {

    KeywordTokenizer kw;

    @After
    public void tearDown() throws Exception {
        kw = null;
    }

    @Test
    public void testGetTokens_limitZero() throws Exception {
        kw = new KeywordTokenizer("o rato roeu a roupa do rei de roma", 0);
        Set<String> tokens = kw.getTokens();
        assertNotNull(tokens);
        assertEquals(9, tokens.size());
    }

    @Test
    public void testGetTokens_limitUmComRepeticao() throws Exception {
        kw = new KeywordTokenizer("o rato roeu a roupa do rei de roma o rato roeu a roupa do rei de roma", 2);
        long before = System.currentTimeMillis();
        Set<String> tokens = kw.getTokens();
        System.out.println(System.currentTimeMillis() - before);
        assertNotNull(tokens);
        assertEquals(7, tokens.size());
    }

    @Test
    public void testGetTokens_doisEspacos() throws Exception {
        kw = new KeywordTokenizer("o rato    roeu a do rei de roma", 2);
        long before = System.currentTimeMillis();
        Set<String> tokens = kw.getTokens();
        System.out.println(System.currentTimeMillis() - before);
        assertNotNull(tokens);
        assertEquals(6, tokens.size());
    }

    @Test
    public void testGetTokens_ordenado() throws Exception {
        kw = new KeywordTokenizer("o rato roeu a roupa do rei de roma", 2);
        long before = System.currentTimeMillis();
        Set<String> tokens = kw.getTokens();
        System.out.println(System.currentTimeMillis() - before);
        assertNotNull(tokens);
        String[] ordenado = new String[]{"de", "do", "rato", "rei", "roeu", "roma", "roupa"};
        assertEquals(7, tokens.size());
        Iterator<String> iterator = tokens.iterator();
        System.out.println(tokens);

        int i = 0;
        while (iterator.hasNext()) {
            String s = iterator.next();
            assertEquals(ordenado[i++], s);
        }


    }

    @Test
    public void testGetTokens_comFiltro() throws Exception {
        Set<String> filtrado = new TreeSet<>();
        filtrado.add("rei");
        filtrado.add("rato");

        kw = new KeywordTokenizer("o rato roeu a roupa do rei de roma", 1, filtrado);
        long before = System.currentTimeMillis();
        Set<String> tokens = kw.getTokens();
        System.out.println(System.currentTimeMillis() - before);
        assertNotNull(tokens);
        assertEquals(7, tokens.size());
        assertFalse(tokens.contains("rei"));
        assertFalse(tokens.contains("rato"));
        assertTrue(tokens.contains("roupa"));

    }


}
package br.com.alexpfx.supermarket.domain;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by alexandre on 02/01/2016.
 */
public class SplitterByIndexTest {


    @Test
    public void testSplit() throws Exception {
        String[] r = SplitterByIndex.split("teste", n -> n % 2 == 0);
        Assert.assertArrayEquals(r, new String[]{"tse", "et"});
    }
}
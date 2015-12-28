package br.com.alexpfx.supermarket.crawler.model.domain;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by alexandre on 27/12/2015.
 */
public class Ean13Test {

    @Test
    public void test1() {
        Ean13 e = new Ean13("7891627314051");
        e = new Ean13("7622300850586");
        e = new Ean13("7894900531008");
        try {
            e = new Ean13("7891627314050");
        } catch (RuntimeException re) {
            Assert.assertEquals(re.getMessage(), "NOT VALID EAN CODE");
        }
        try {
            e = new Ean13("7891627314056");
        } catch (RuntimeException re) {
            Assert.assertEquals(re.getMessage(), "NOT VALID EAN CODE");
        }
        try {
            e = new Ean13("7891627314052");
        } catch (RuntimeException re) {
            Assert.assertEquals(re.getMessage(), "NOT VALID EAN CODE");
        }
        try {
            e = new Ean13("7891627314053");
        } catch (RuntimeException re) {
            Assert.assertEquals(re.getMessage(), "NOT VALID EAN CODE");
        }
        try {
            e = new Ean13("7891627314054");
        } catch (RuntimeException re) {
            Assert.assertEquals(re.getMessage(), "NOT VALID EAN CODE");
        }

        try {
            e = new Ean13("7891627314055");
        } catch (RuntimeException re) {
            Assert.assertEquals(re.getMessage(), "NOT VALID EAN CODE");
        }
    }


}
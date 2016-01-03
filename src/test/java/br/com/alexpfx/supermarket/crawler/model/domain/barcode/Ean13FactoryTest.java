package br.com.alexpfx.supermarket.crawler.model.domain.barcode;

import br.com.alexpfx.supermarket.crawler.model.domain.barcode.Ean13Factory;
import br.com.alexpfx.supermarket.crawler.model.exception.InvalidBarCodeException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by alexandre on 03/01/2016.
 */
public class Ean13FactoryTest {

    Ean13Factory factory;

    @Before
    public void setup() {
        factory = new Ean13Factory();
    }

    @Test
    public void testCreateValids() throws Exception {
        factory.create("7891627314051");
        factory.create("7622300850586");
        factory.create("7894900531008");
    }

    @Test
    public void testCreateInvalids() {
        try {
            factory.create("   7894900531008");
            fail();
        } catch (InvalidBarCodeException e) {

        }
        try {
            factory.create("4343345532434");
            fail();
        } catch (InvalidBarCodeException e) {

        }
        try {
            factory.create("1234567784552");
            fail();
        } catch (InvalidBarCodeException e) {

        }

        try {
            factory.create(null);
            fail();
        } catch (InvalidBarCodeException e) {

        }


    }

}
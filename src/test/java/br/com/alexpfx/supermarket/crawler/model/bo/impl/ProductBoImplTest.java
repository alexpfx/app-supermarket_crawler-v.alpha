package br.com.alexpfx.supermarket.crawler.model.bo.impl;

import br.com.alexpfx.supermarket.crawler.model.bo.ProductBo;
import br.com.alexpfx.supermarket.crawler.model.domain.Manufacturer;
import br.com.alexpfx.supermarket.crawler.model.domain.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by alexandre on 29/12/2015.
 */
public class ProductBoImplTest {
    private ProductBo productBo;

    @Before
    public void setUp() {
        productBo = new ProductBoImpl();

    }

    @After
    public void tearDown() {
        productBo = null;
    }


    private Product createProduct(String description) {
        Product p = new Product();
        p.setDescription(description);
        p.setUrl("url");
        p.setManufacturer(new Manufacturer(1, "fabrica"));
        return p;
    }

    @Test
    public void testSave() throws Exception {
        Product product = createProduct("produto 1");
        productBo.save(product);
    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testFind() throws Exception {

    }
}
package br.com.alexpfx.supermarket.crawler;

import br.com.alexpfx.supermarket.bo.ProductBo;
import br.com.alexpfx.supermarket.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by alexandre on 01/01/2016.ervi
 */
public class MercadoRibeiraoProductExtractListener implements ProductExtractedListener {

    @Autowired
    private ProductBo productBo;

    @Override
    public void productExtracted(Product product) {
        productBo.save(product);
    }
}

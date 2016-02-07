package br.com.alexpfx.supermarket.batch.processor;

import br.com.alexpfx.supermarket.domain.Product;
import br.com.alexpfx.supermarket.webcrawler.to.TransferObject;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by alexandre on 07/02/2016.
 */
public class AngeloniProductProcessor implements ItemProcessor<TransferObject, Product> {
    @Override
    public Product process(TransferObject transferObject) throws Exception {
        return null;
    }
}

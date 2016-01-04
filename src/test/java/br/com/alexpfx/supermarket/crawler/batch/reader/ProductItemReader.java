package br.com.alexpfx.supermarket.crawler.batch.reader;

import br.com.alexpfx.supermarket.crawler.model.domain.Product;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 * Created by alexandre on 04/01/2016.
 */
public class ProductItemReader implements ItemReader<Product> {
    @Override
    public Product read() throws UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}

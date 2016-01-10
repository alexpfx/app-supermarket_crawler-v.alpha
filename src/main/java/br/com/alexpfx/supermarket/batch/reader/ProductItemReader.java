package br.com.alexpfx.supermarket.batch.reader;

import br.com.alexpfx.supermarket.webcrawler.to.TransferObject;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by alexandre on 04/01/2016.
 */
public class ProductItemReader implements ItemReader<TransferObject> {

    @Autowired
    private ProductList productList;

    @Override
    public TransferObject read() throws UnexpectedInputException, ParseException, NonTransientResourceException {
        TransferObject product = productList.get();
        return product;
    }
}

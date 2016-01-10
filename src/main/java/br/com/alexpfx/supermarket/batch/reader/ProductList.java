package br.com.alexpfx.supermarket.batch.reader;

import br.com.alexpfx.supermarket.domain.Product;
import br.com.alexpfx.supermarket.webcrawler.to.TransferObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 04/01/2016.
 */
public class ProductList {

    private List<Product> products = new ArrayList<>();
    private int index = 0;
    public void add (Product product){
        products.add(product);
    }

    public Product get (){
        if (index < products.size())
            return products.get(index++);
        return null;
    }

}

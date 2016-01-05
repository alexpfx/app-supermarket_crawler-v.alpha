package br.com.alexpfx.supermarket.batch.reader;

import br.com.alexpfx.supermarket.domain.Product;

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
        return products.get(index++);
    }

}

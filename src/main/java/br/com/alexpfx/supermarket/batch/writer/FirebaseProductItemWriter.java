package br.com.alexpfx.supermarket.batch.writer;

import br.com.alexpfx.supermarket.domain.Product;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * Created by alexandre on 07/02/2016.
 */
public class FirebaseProductItemWriter implements ItemWriter<Product> {
    @Override
    public void write(List<? extends Product> list) throws Exception {

    }
}

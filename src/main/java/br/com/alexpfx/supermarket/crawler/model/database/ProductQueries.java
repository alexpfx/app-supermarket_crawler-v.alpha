package br.com.alexpfx.supermarket.crawler.model.database;

/**
 * Created by alexandre on 17/12/2015.
 */
public class ProductQueries implements SqlQueries {
    private String insert;


    @Override
    public String toString() {
        return insert;
    }
}

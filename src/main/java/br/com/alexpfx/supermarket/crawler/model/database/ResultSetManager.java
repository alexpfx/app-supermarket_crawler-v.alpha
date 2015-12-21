package br.com.alexpfx.supermarket.crawler.model.database;

/**
 * Created by alexandre on 21/12/2015.
 */
public interface ResultSetManager {
    Boolean nextBoolean(int index);

    Integer nextInteger(int index);
}

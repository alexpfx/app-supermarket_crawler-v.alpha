package br.com.alexpfx.supermarket.crawler.model.database;

import com.firebase.client.Firebase;

/**
 * Created by alexandre on 08/12/2015.
 */
public interface Repository <T> {

    void save (String path, T value) throws InterruptedException;

}

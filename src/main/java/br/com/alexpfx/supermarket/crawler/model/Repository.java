package br.com.alexpfx.supermarket.crawler.model;

import com.firebase.client.Firebase;

/**
 * Created by alexandre on 08/12/2015.
 */
public interface Repository <T> {

    void save (String category, String key, T value) throws InterruptedException;

}

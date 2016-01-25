package br.com.alexpfx.supermarket.webcrawler.crawler;

import com.jaunt.UserAgent;

import java.util.List;

/**
 * Created by alexandre on 17/01/2016.
 */
public interface Collector<T> {
    List<T> collect(List<String> urls);
}

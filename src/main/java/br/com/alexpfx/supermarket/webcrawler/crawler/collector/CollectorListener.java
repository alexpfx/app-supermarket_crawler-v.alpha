package br.com.alexpfx.supermarket.webcrawler.crawler.collector;

/**
 * Created by alexandre on 17/01/2016.
 */
public interface CollectorListener<T> {
    void collected(T item);


    CollectorListener EMPTY = new EMPTY<>();

    class EMPTY<T> implements CollectorListener<T> {
        public void collected(T t) {

        }
    }


}

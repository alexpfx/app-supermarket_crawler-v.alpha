package br.com.alexpfx.supermarket.crawler.model.database;

import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by alexandre on 20/12/2015.
 */
public interface QueryParams {

    QueryParams EMPTY = new QueryParams() {
        @Override
        public Object get(Integer paramIndex) {
            return null;
        }

        @Override
        public void forEach(Consumer<? super Map.Entry<Integer, Object>> action) {

        }
    };
    Object get(Integer paramIndex);
    void forEach(Consumer<? super Map.Entry<Integer, Object>> action);
}

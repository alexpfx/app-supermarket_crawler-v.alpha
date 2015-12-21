package br.com.alexpfx.supermarket.crawler.model.database;

import java.util.*;
import java.util.function.Consumer;

/**
 * Created by alexandre on 20/12/2015.
 */
public class QueryParamsImpl implements QueryParams {

    private Map<Integer, Object> params = new HashMap<>();

    public QueryParamsImpl(Object ... params) {
        int i = 1;
        for (Object param : params) {
            this.params.put(i++, param);
        }
    }

    @Override
    public Object get(Integer paramIndex) {
        return params.get(paramIndex);
    }

    @Override
    public void forEach(Consumer<? super Map.Entry<Integer, Object>> action) {
        params.entrySet().forEach(action);
    }


}

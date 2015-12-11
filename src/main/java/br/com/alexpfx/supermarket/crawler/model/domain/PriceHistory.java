package br.com.alexpfx.supermarket.crawler.model.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 09/12/2015.
 */
public class PriceHistory {

    private List<PriceDate> priceHistory = new ArrayList<>();

    public void add (PriceDate priceDate){
        priceHistory.add(priceDate);
    }

    public List<PriceDate> getPriceHistory() {
        return priceHistory;
    }
}

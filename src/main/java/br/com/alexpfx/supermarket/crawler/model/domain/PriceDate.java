package br.com.alexpfx.supermarket.crawler.model.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by alexandre on 09/12/2015.
 */
public class PriceDate {

    private BigDecimal price;

    private Date date;

    private PriceDate(Date date, BigDecimal price) {
        this.date = date;
        this.price = price;
    }

    public static PriceDate of(Date date, BigDecimal price) {
        return new PriceDate(date, price);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }
}

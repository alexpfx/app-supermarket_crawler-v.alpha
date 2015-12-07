package br.com.alexpfx.supermarket.crawler.model;

import com.drew.lang.annotations.Nullable;

import java.math.BigDecimal;
import java.util.Formattable;
import java.util.Formatter;

/**
 * Created by alexandre on 06/12/2015.
 */
public class SimpleProductInfo implements ProductInfo {

    private String identity;
    private String description;
    private BigDecimal price;
    @Nullable
    private QuantityMeasurement quantity;


    private SimpleProductInfo(String identity, String description, BigDecimal price, QuantityMeasurement quantity) {
        this.identity = identity;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public static SimpleProductInfo of(String identity, String description, BigDecimal price, QuantityMeasurement quantity) {
        return new SimpleProductInfo(identity, description, price, quantity);
    }

    public static SimpleProductInfo of(String identity, String description, BigDecimal price) {
        return new SimpleProductInfo(identity, description, price, null);
    }


    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getIdentity() {
        return identity;
    }

    public QuantityMeasurement getQuantity() {
        return quantity;
    }


    @Override
    public String toString() {
        return String.format("Cod: %s Desc: %s Price: %s", identity, description, price);
    }
}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleProductInfo that = (SimpleProductInfo) o;

        if (!identity.equals(that.identity)) return false;
        if (!description.equals(that.description)) return false;
        if (!price.equals(that.price)) return false;
        return !(quantity != null ? !quantity.equals(that.quantity) : that.quantity != null);

    }

    @Override
    public int hashCode() {
        int result = identity.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }
}

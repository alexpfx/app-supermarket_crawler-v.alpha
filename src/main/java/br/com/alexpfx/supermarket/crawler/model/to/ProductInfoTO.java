package br.com.alexpfx.supermarket.crawler.model.to;

import com.google.common.base.CharMatcher;

import java.math.BigDecimal;

/**
 * Created by alexandre on 06/12/2015.
 * Vem do crawler.
 */
public class ProductInfoTO {

    private String id;
    private String description;
    private BigDecimal price;

    private String url;

    private String imageUrl;
    private String quantity;
    private String unity;

    public ProductInfoTO(String id, String description, BigDecimal price, String url, String imageUrl, String quantity, String unity) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.url = url;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
        this.unity = unity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getUnity() {
        return unity;
    }

    @Override
    public String toString() {
        return String.format("Cod: %s Desc: %s Price: %s", id, description, price);
    }

    public boolean isValidEan() {
        return CharMatcher.DIGIT.matchesAllOf(id);
    }

    public String getUrl() {
        return url;
    }




}

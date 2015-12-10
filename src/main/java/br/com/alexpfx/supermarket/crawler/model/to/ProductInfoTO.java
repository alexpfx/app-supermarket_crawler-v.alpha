package br.com.alexpfx.supermarket.crawler.model.to;

import java.math.BigDecimal;

/**
 * Created by alexandre on 06/12/2015.
 */
public class ProductInfoTO  {


    private String id;
    private String description;
    private BigDecimal price;

    private String imageUrl;
    private String quantity;
    private String unity;

    public ProductInfoTO(String id, String description, BigDecimal price, String imageUrl, String quantity, String unity) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
        this.unity = unity;
    }

    private ProductInfoTO(String id, String description, BigDecimal price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }


    public static ProductInfoTO of(String identity, String description, BigDecimal price) {
        return new ProductInfoTO(identity, description, price);
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

















}

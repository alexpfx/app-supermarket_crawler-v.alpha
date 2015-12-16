package br.com.alexpfx.supermarket.crawler.model.to;

import java.math.BigDecimal;

public class ProductInfoTOBuilder {
    private String id;
    private String description;
    private BigDecimal price;
    private String url;
    private String imageUrl;
    private String quantity;
    private String unity;

    public ProductInfoTOBuilder id(String id) {
        this.id = id;
        return this;
    }

    public ProductInfoTOBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ProductInfoTOBuilder price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ProductInfoTOBuilder url(String url) {
        this.url = url;
        return this;
    }

    public ProductInfoTOBuilder imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public ProductInfoTOBuilder quantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public ProductInfoTOBuilder unity(String unity) {
        this.unity = unity;
        return this;
    }

    public ProductInfoTO createProductInfoTO() {
        return new ProductInfoTO(id, description, price, url, imageUrl, quantity, unity);
    }
}
package br.com.alexpfx.supermarket.crawler.model.domain;

public class ProductBuilder {
    private ProductIdentityEan productIdentity;
    private Manufacturer manufacturer;
    private String description;
    private Keywords keywords;
    private String url;

    public ProductBuilder productIdentity(ProductIdentityEan productIdentity) {
        this.productIdentity = productIdentity;
        return this;
    }

    public ProductBuilder manufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public ProductBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ProductBuilder keywords(Keywords keywords) {
        this.keywords = keywords;
        return this;
    }

    public ProductBuilder url(String url) {
        this.url = url;
        return this;
    }

    public Product createProduct() {
        return new Product(productIdentity, manufacturer, description, keywords, url);
    }
}
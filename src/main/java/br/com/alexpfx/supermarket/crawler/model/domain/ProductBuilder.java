package br.com.alexpfx.supermarket.crawler.model.domain;

public class ProductBuilder {
    private Integer id;
    private Manufacturer manufacturer;
    private String description;
    private String url;
    private Keywords keywords;

    public ProductBuilder id(Integer id) {
        this.id = id;
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

    public ProductBuilder url(String url) {
        this.url = url;
        return this;
    }

    public ProductBuilder keywords(Keywords keywords) {
        this.keywords = keywords;
        return this;
    }

    public Product createProduct() {
        return new Product(id, manufacturer, description, url, keywords);
    }
}
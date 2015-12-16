package br.com.alexpfx.supermarket.crawler.model.domain;

public class ProductBuilder {
    private int id;
    private BarCode barCode;
    private String alternativeId;
    private Manufacturer manufacturer;
    private String description;
    private Keywords keywords;
    private String url;

    public ProductBuilder id(int id) {
        this.id = id;
        return this;
    }

    public ProductBuilder barCode(BarCode barCode) {
        this.barCode = barCode;
        return this;
    }

    public ProductBuilder alternativeId(String alternativeId) {
        this.alternativeId = alternativeId;
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
        return new Product(id, barCode, alternativeId, manufacturer, description, keywords, url);
    }
}
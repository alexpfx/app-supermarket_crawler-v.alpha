package br.com.alexpfx.supermarket.crawler.model.domain;

/**
 * Created by alexandre on 09/12/2015.
 */
public class Product {

    private ProductIdentity productIdentity;

    private Manufacturer manufacturer;

    private String description;

    private Keywords keywords;

    private String url;

    public Product(ProductIdentityEan productIdentity, Manufacturer manufacturer, String description, Keywords keywords, String url) {
        this.productIdentity = productIdentity;
        this.manufacturer = manufacturer;
        this.description = description;
        this.keywords = keywords;
        this.url = url;
    }

    @Override
    public String toString() {
        return String.format("%63s\t%63s\t%100s", description, productIdentity, keywords);

    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public Keywords getKeywords() {
        return keywords;
    }

    public String getUrl() {
        return url;
    }

    public String getProductId (){
        return productIdentity.getCode();
    }

    public ProductIdentityType getIdentityType (){
        return productIdentity.getType();
    }

}

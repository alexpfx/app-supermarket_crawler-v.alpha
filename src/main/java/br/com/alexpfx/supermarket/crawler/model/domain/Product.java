package br.com.alexpfx.supermarket.crawler.model.domain;

/**
 * Created by alexandre on 09/12/2015.
 */
public class Product {
    private int id;

    private BarCode barCode;

    private Manufacturer manufacturer;

    private String description;

    private Keywords keywords;


    private Product(BarCode barCode, String description, Keywords keywords) {
        this.barCode = barCode;
        this.description = description;
        this.keywords = keywords;
    }

    private Product(Seller seller, String description, PriceHistory priceHistory, Keywords keywords) {
        this.description = description;
        this.keywords = keywords;
    }

    public static Product of(Seller seller, String description, PriceHistory priceHistory, Keywords keywords) {
        return new Product(seller, description, priceHistory, keywords);
    }

    public static Product of(BarCode barCode, String description, Keywords keywords) {
        return new Product(barCode, description, keywords);
    }

    @Override
    public String toString() {
        return String.format("%63s\t%63s\t%100s", description, barCode, keywords);

    }

    public String getDescription() {
        return description;
    }


    public Keywords getKeywords() {
        return keywords;
    }

    public BarCode getBarCode() {
        return barCode;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

}

package br.com.alexpfx.supermarket.crawler.model.domain;

/**
 * Created by alexandre on 09/12/2015.
 */
public class Product {

    private Seller seller;
    private String description;
    private PriceHistory priceHistory;
    private Keywords keywords;

    private Product(Seller seller, String description, PriceHistory priceHistory, Keywords keywords) {
        this.seller = seller;
        this.description = description;
        this.priceHistory = priceHistory;
        this.keywords = keywords;
    }

    public static Product of(Seller seller, String description, PriceHistory priceHistory, Keywords keywords) {
        return new Product(seller, description, priceHistory, keywords);
    }


    public Seller getSeller() {
        return seller;
    }

    public String getDescription() {
        return description;
    }

    public PriceHistory getPriceHistory() {
        return priceHistory;
    }

    public Keywords getKeywords() {
        return keywords;
    }
}

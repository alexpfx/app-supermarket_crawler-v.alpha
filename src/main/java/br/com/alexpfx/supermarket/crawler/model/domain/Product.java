package br.com.alexpfx.supermarket.crawler.model.domain;

/**
 * Created by alexandre on 09/12/2015.
 */
public class Product {
    private int id;

    private BarCode barCode;

    private String alternativeId;

    private Manufacturer manufacturer;

    private String description;

    private Keywords keywords;

    private String url;


    public Product(int id, BarCode barCode, String alternativeId, Manufacturer manufacturer, String description, Keywords keywords, String url) {
        this.id = id;
        this.barCode = barCode;
        this.alternativeId = alternativeId;
        this.manufacturer = manufacturer;
        this.description = description;
        this.keywords = keywords;
        this.url = url;
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


    public boolean hasEanCode() {
        return barCode.isEan();
    }

    public String getAlternativeId() {
        return alternativeId;
    }

    public String getUrl() {
        return url;
    }
}

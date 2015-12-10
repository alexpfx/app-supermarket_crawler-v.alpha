package br.com.alexpfx.supermarket.crawler.model.domain;

/**
 * Created by alexandre on 09/12/2015.
 */
public class Seller {

    private String name;
    private String localization;

    private Seller(String name, String localization) {
        this.name = name;
        this.localization = localization;
    }

    private Seller(String name) {
        this.name = name;
    }

    public static Seller of(String name, String localization) {
        return new Seller(name, localization);
    }

    public static Seller of(String name) {
        return new Seller(name);
    }


    public String getName() {
        return name;
    }

    public String getLocalization() {
        return localization;
    }
}

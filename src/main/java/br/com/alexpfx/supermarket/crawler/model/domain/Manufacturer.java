package br.com.alexpfx.supermarket.crawler.model.domain;


/**
 * Created by alexandre on 12/12/2015.
 */
public class Manufacturer {
    private int id;

    private String name;

    public Manufacturer() {
    }

    public Manufacturer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

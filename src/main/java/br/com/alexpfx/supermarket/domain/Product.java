package br.com.alexpfx.supermarket.domain;


import br.com.alexpfx.supermarket.domain.barcode.Ean13;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by alexandre on 09/12/2015.
 */
@Entity
@Table(name = "tb_produtos")
public class Product implements Serializable {

    public Product() {
    }

    public Product(Integer id, Manufacturer manufacturer, String description, String url, Ean13 ean,
                   Keywords keywords) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.description = description;
        this.url = url;
        this.ean = ean;
        this.keywords = keywords;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_FABRICANTE")
    private Manufacturer manufacturer;

    @Column(name = "DESCRICAO")
    private String description;

    @Column(name = "URL")
    private String url;

    @Embedded
    private Ean13 ean;

    @Transient
    private Keywords keywords;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public void setEan(Ean13 ean) {
        this.ean = ean;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Keywords getKeywords() {
        return keywords;
    }

    public void setKeywords(Keywords keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return String.format("%63s\t%63s\t%100s", description, ean, keywords);

    }
}

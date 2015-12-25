package br.com.alexpfx.supermarket.crawler.model.domain;

import javax.naming.NamingEnumeration;
import javax.persistence.*;

/**
 * Created by alexandre on 09/12/2015.
 */
@javax.persistence.Entity
@Table(name = "PRODUTOS")
public class Product implements Entity {
    Product(Integer id, Manufacturer manufacturer, String description, String url, Keywords keywords) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.description = description;
        this.url = url;
        this.keywords = keywords;
    }

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_FABRICANTE")
    private Manufacturer manufacturer;

    @Column(name = "DESCRICAO")
    private String description;

    @Column(name = "URL")
    private String url;

    @Transient
    private Keywords keywords;

}

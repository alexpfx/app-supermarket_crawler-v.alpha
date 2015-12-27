package br.com.alexpfx.supermarket.crawler.model.domain;


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

    @Column(name = "EAN_CODE")
    private String eanCode;

    @Transient
    private Keywords keywords;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}

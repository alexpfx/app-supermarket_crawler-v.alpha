package br.com.alexpfx.supermarket.crawler.model.domain;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by alexandre on 12/12/2015.
 */
@javax.persistence.Entity
@Table(name = "tb_fabricante")
public class Manufacturer implements BaseEntity {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "nome")
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

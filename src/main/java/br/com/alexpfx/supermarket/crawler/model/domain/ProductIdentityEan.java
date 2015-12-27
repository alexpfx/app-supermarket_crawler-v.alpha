package br.com.alexpfx.supermarket.crawler.model.domain;


import javax.persistence.*;
import javax.persistence.Entity;

public class ProductIdentityEan implements ProductIdentity {

    private String code;

    private ProductIdentityType type;

    public ProductIdentityEan(String code) {
        type = ProductIdentityType.EAN;
    }

}

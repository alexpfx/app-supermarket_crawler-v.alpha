package br.com.alexpfx.supermarket.crawler.model.domain;


import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Created by alexandre on 12/12/2015.
 */
@Entity
@Table(name = "Identificador_Produto")
@DiscriminatorValue(value = "E")
@DiscriminatorColumn(name = "tipo_identificador")
public class ProductIdentityEan implements ProductIdentity {

    @Column(name = "codigo")
    private String code;

    @Column(name = "tipo_identificador")
    private ProductIdentityType type;

    public ProductIdentityEan(String code) {
        type = ProductIdentityType.EAN;
    }

}

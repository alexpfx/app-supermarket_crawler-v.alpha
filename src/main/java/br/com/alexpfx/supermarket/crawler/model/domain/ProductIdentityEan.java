package br.com.alexpfx.supermarket.crawler.model.domain;


/**
 * Created by alexandre on 12/12/2015.
 */
public class ProductIdentityEan implements ProductIdentity {

    private String code;

    private ProductIdentityType type;

    public ProductIdentityEan(String code) {
        type = ProductIdentityType.EAN;
    }


    @Override
    public String getCode() {
        return code;
    }

    @Override
    public ProductIdentityType getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("%63s\t", code);
    }


}

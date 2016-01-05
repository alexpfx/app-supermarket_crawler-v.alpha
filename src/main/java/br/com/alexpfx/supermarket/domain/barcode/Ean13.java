package br.com.alexpfx.supermarket.domain.barcode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by alexandre on 27/12/2015.
 */
@Embeddable
public class Ean13 implements BarCode {

    @Column(name = "ean_code", nullable = true, length = 13)
    private String code;

    public Ean13() {
    }

    public Ean13(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}

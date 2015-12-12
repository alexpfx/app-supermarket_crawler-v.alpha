package br.com.alexpfx.supermarket.crawler.model.domain;

/**
 * Created by alexandre on 12/12/2015.
 */
public class BarCode {
    private String code;
    private BarCodeType type;

    public BarCode(String code, BarCodeType type) {
        this.code = code;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public BarCodeType getType() {
        return type;
    }
}

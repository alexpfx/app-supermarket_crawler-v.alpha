package br.com.alexpfx.supermarket.crawler.model.domain;


/**
 * Created by alexandre on 12/12/2015.
 */
public class BarCode {

    private String code = "";

    private BarCodeType type;


    private BarCode(String code, BarCodeType type) {
        this.code = code;
        this.type = type;
    }

    public static BarCode of(String code, BarCodeType type) {
        return new BarCode(code, type);
    }

    public String getCode() {
        return code;
    }

    public BarCodeType getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("%63s\t", code);
    }

    public boolean isEan (){
        return BarCodeType.EAN.equals(type);
    }

}

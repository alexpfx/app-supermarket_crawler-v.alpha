package br.com.alexpfx.supermarket.crawler.model.domain.barcode;

import br.com.alexpfx.supermarket.crawler.model.exception.InvalidBarCodeException;

/**
 * Created by alexandre on 03/01/2016.
 */
public class Ean13Factory implements BarCodeFactory {

    @Override
    public BarCode create(String code) throws InvalidBarCodeException {
        if (!isValid(code, new Ean13Validation())){
            throw new InvalidBarCodeException(code);
        }
        return new Ean13(code);
    }
}

package br.com.alexpfx.supermarket.domain.barcode;

import br.com.alexpfx.supermarket.crawler.model.exception.InvalidBarCodeException;

/**
 * Created by alexandre on 03/01/2016.
 */
public class Ean13Factory implements BarCodeFactory {

    @Override
    public BarCode create(String code) throws InvalidBarCodeException {
        if (!isValid(code, BarCodePredicate.isValidEan13())) {
            throw new InvalidBarCodeException(code);
        }
        return new Ean13(code);
    }
}

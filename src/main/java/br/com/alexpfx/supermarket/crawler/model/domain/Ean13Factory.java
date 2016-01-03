package br.com.alexpfx.supermarket.crawler.model.domain;

import br.com.alexpfx.supermarket.crawler.model.exception.InvalidEANCodeException;

/**
 * Created by alexandre on 03/01/2016.
 */
public class Ean13Factory implements BarCodeFactory {

    @Override
    public BarCode create(String code) throws InvalidEANCodeException {
        if (!isValid(code, new Ean13Validation())){
            throw new InvalidEANCodeException(code);
        }
        return new Ean13(code);
    }
}

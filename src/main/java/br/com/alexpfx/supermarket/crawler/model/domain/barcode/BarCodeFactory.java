package br.com.alexpfx.supermarket.crawler.model.domain.barcode;

import br.com.alexpfx.supermarket.crawler.model.exception.InvalidBarCodeException;

import java.util.function.Predicate;

/**
 * Created by alexandre on 03/01/2016.
 */
public interface BarCodeFactory {
    BarCode create(String code) throws InvalidBarCodeException;

    default boolean isValid(String code, Predicate<String> predicate) {
        return predicate.test(code);
    }

}

package br.com.alexpfx.supermarket.crawler.model.domain;

import br.com.alexpfx.supermarket.crawler.model.exception.InvalidEANCodeException;

import java.util.function.Predicate;

/**
 * Created by alexandre on 03/01/2016.
 */
public interface BarCodeFactory {
    BarCode create(String code) throws InvalidEANCodeException;

    default boolean isValid(String code, Predicate<String> predicate) {
        return predicate.test(code);
    }

}

package br.com.alexpfx.supermarket.crawler;

import com.google.common.base.Predicate;

/**
 * Created by alexandre on 07/12/2015.
 */
public class DigitOrComma implements Predicate<Character> {
    public boolean apply(Character character) {
        return Character.isDigit(character) || character == ',';
    }
}

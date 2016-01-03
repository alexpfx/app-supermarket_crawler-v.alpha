package br.com.alexpfx.supermarket.crawler.model.domain;

import br.com.alexpfx.supermarket.crawler.model.exception.InvalidEANCodeException;
import com.google.common.base.CharMatcher;
import com.google.common.base.Predicates;

import java.util.function.Predicate;

/**
 * Created by alexandre on 03/01/2016.
 */
public class Ean13Validation implements Predicate<String> {

    @Override
    public boolean test(String s) {
        return isValid(s);
    }

    private boolean isValid(String code) {
        if (code == null || code.length() != 13) {
            return false;
        }
        if (!CharMatcher.DIGIT.matchesAllOf(code)) {
            return false;
        }
        String codeWithoutVd = code.substring(0, 12);
        int pretendVd = Integer.valueOf(code.substring(12, 13));
        String[] evenOdd = SplitterByIndex.split(codeWithoutVd, idx -> idx % 2 == 0);
        int evenSum = sumStringDigits(evenOdd[0]);
        int oddSum = sumStringDigits(evenOdd[1]);
        int oddFator = oddSum * 3;
        int sumResult = oddFator + evenSum;
        int dv = getEanVd(sumResult);
        if (pretendVd != dv) {
            return false;
        }
        return true;
    }

    private int sumStringDigits(String s) {
        return s.chars().map(n ->
                Character.getNumericValue(n)
        ).sum();
    }

    private int getEanVd(int s) {
        return 10 - (s % 10);
    }


}
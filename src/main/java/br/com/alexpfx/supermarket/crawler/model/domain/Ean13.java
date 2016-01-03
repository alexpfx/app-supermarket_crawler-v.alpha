package br.com.alexpfx.supermarket.crawler.model.domain;

import br.com.alexpfx.supermarket.crawler.model.exception.InvalidEANCodeException;
import com.google.common.base.CharMatcher;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by alexandre on 27/12/2015.
 */
@Embeddable
public class Ean13 {

    @Column(name = "ean_code", nullable = true, length = 13)
    private String code;

    public Ean13() {
    }

    public Ean13(String code) {
        validate(code);
        this.code = code;
    }

    private void validate(String code) {
        if (code == null || code.length() != 13) {
            throw new InvalidEANCodeException(code);
        }
        if (!CharMatcher.DIGIT.matchesAllOf(code)) {
            throw new InvalidEANCodeException(code);
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
            throw new InvalidEANCodeException(code);
        }
    }

    private int sumStringDigits(String s) {
        return s.chars().map(n ->
                Character.getNumericValue(n)
        ).sum();
    }

    private int getEanVd(int s) {
        return 10 - (s % 10);
    }

    @Override
    public String toString() {
        return code;
    }
}

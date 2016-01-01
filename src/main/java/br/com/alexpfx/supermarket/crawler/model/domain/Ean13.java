package br.com.alexpfx.supermarket.crawler.model.domain;

import com.google.common.base.CharMatcher;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by alexandre on 27/12/2015.
 */
@Embeddable
public class Ean13 {
    private static final RuntimeException NOT_VALID_EAN_EXCEPTION = new RuntimeException("NOT VALID EAN CODE");

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
            throw NOT_VALID_EAN_EXCEPTION;
        }
        if (!CharMatcher.DIGIT.matchesAllOf(code)) {
            throw NOT_VALID_EAN_EXCEPTION;
        }
        String codeWithoutVd = code.substring(0, 12);
        int pretendVd = Integer.valueOf(code.substring(12, 13));
        int e = sumEven(codeWithoutVd);
        int o = sumOdd(codeWithoutVd);
        int me = o * 3;
        int s = me + e;
        int dv = getEanVd(s);
        if (!(pretendVd == dv)) {
            throw NOT_VALID_EAN_EXCEPTION;
        }
    }

    private int getEanVd(int s) {
        return 10 - (s % 10);
    }

    private int sumEven(String code) {
        int sum = 0;
        for (int i = 0; i < code.length(); i++) {
            if (isEven(i)) {
                sum += Character.getNumericValue(code.charAt(i));
            }
        }
        return sum;
    }


    private int sumOdd(String code) {
        int sum = 0;
        for (int i = 0; i < code.length(); i++) {
            if (!isEven(i)) {
                sum += Character.getNumericValue(code.charAt(i));
            }
        }
        return sum;
    }


    private boolean isEven(int i) {
        return i % 2 == 0;
    }

    @Override
    public String toString() {
        return code;
    }
}

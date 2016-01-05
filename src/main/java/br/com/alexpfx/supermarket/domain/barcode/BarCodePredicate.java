package br.com.alexpfx.supermarket.domain.barcode;

import br.com.alexpfx.supermarket.domain.SplitterByIndex;
import com.google.common.base.CharMatcher;

import java.util.function.Predicate;

/**
 * Created by alexandre on 03/01/2016.
 */
public class BarCodePredicate {

    public static Predicate<String> isValidEan13() {
        return p -> isValid(p);
    }


    private static boolean isValid(String code) {
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

    private static int sumStringDigits(String s) {
        return s.chars().map(n ->
                Character.getNumericValue(n)
        ).sum();
    }

    private static int getEanVd(int s) {
        return 10 - (s % 10);
    }

}
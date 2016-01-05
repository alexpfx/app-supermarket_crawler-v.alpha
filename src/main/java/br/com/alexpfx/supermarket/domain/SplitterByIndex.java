package br.com.alexpfx.supermarket.domain;

import java.util.function.Predicate;

/**
 * Created by alexandre on 02/01/2016.
 */
public interface SplitterByIndex {
    static String[] split(String str, Predicate<Integer> p) {
        String[] ss = new String[2];
        StringBuilder sb0 = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();

        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (p.test(i)) {
                sb0.append(chars[i]);
            } else {
                sb1.append(chars[i]);
            }
        }
        ss[0] = sb0.toString();
        ss[1] = sb1.toString();
        return ss;
    }
}

package br.com.alexpfx.supermarket.domain;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by alexandre on 13/02/2016.
 */
public enum MeasureUnit {

    MG("mg", "miligrama"), G("g", "grama"), GR("gr", "grama"), KG("kg", "kilograma"), ML("ml", "Mililitro"), L("l",
                                                                                                               "Litro");

    private String name;
    private String acronym;

    MeasureUnit(String acronym, String name) {
        this.acronym = acronym;
        this.name = name;
    }

    public static MeasureUnit getByAcronym(String acronym) {
        final Stream<MeasureUnit> stream = Arrays.stream(values());
        final Optional<MeasureUnit> first = stream.filter(
                measureUnit -> measureUnit.acronym.equalsIgnoreCase(acronym)).findFirst();
        return first == null ? null : first.get();

    }

    public static String getRegex() {
        EnumSet<MeasureUnit> range = EnumSet.allOf(MeasureUnit.class);
        StringBuilder sb = new StringBuilder();
        Indexer indexer = new Indexer();
        range.stream().forEach(
                measureUnit -> {
                    sb.append(measureUnit.acronym);
                    if (indexer.i < range.size() - 1)
                        sb.append("|");

                    indexer.i++;
                }
        );
        return sb.toString();
    }

    static class Indexer {
        int i = 0;
    }

    public enum Patterns {

        QUANTITY_UNIT("(?i)([0-9]+)(" + MeasureUnit.getRegex() + ")");

        private Pattern pattern;

        Patterns(String patternString) {
            pattern = pattern.compile(patternString);
        }

        public Pattern getPattern() {
            return pattern;
        }

        public Matcher getMatcher(CharSequence input) {
            return getPattern().matcher(input);
        }


        public String getQuantity(CharSequence input) {
            final int group_idx = 1;

            Matcher matcher = getMatcher(input);
            boolean found = matcher.find();
            return found ? toLower(matcher.group(group_idx)) : "";
        }

        private String toLower(String input) {
            return input.toLowerCase();
        }

        public String getUnity(CharSequence input) {
            final int group_idx = 2;

            Matcher matcher = getMatcher(input);
            boolean found = matcher.find();
            return found ? toLower(matcher.group(group_idx)) : "";
        }
    }
}


package br.com.alexpfx.supermarket.domain;

import java.util.EnumSet;

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

    static class Indexer {
        int i = 0;
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

}

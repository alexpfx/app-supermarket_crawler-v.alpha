package br.com.alexpfx.supermarket.domain.barcode;

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

}

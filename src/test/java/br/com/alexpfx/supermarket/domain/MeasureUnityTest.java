package br.com.alexpfx.supermarket.domain;

import org.junit.Assert;
import org.junit.Test;

import static br.com.alexpfx.supermarket.domain.MeasureUnit.Patterns.QUANTITY_UNIT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by alexandre on 13/02/2016.
 */
public class MeasureUnityTest {


    @Test
    public void quantity() {
        String testString = "abc1kgabc1l";
        String fg = QUANTITY_UNIT.getQuantity(testString);
        assertEquals("1", fg);
    }


    @Test
    public void unity() {
        String testString = "abc1kgabc1l";
        String fg = QUANTITY_UNIT.getUnity(testString);
        assertEquals("kg", fg);
    }

    @Test
    public void unityUpperCase() {
        String testString = "abc1KGabc1l";
        String fg = QUANTITY_UNIT.getUnity(testString);
        assertEquals("kg", fg);
    }


    @Test
    public void unityNoOccurrence() {
        String testString = "fasfasfasfaf";
        String fg = QUANTITY_UNIT.getQuantity(testString);
        assertEquals("", fg);
    }

    @Test
    public void unityEmptyString() {
        String testString = "";
        String fg = QUANTITY_UNIT.getQuantity(testString);
        assertEquals("", fg);
    }

    /* If more than one matches, return the first*/
    @Test
    public void unityMoreThanOne() {
        String testString = "abc5mlabc5kg";
        String fg = QUANTITY_UNIT.getUnity(testString);
        assertEquals("ml", fg);
    }

    /* If more than one matches, return the first*/
    @Test
    public void quantityMoreThanOne() {
        String testString = "abcm5mlabc1kg";
        String fg = QUANTITY_UNIT.getQuantity(testString);
        assertEquals("5", fg);
    }



    @Test
    public void getByAcronym(){
        final MeasureUnit ml = MeasureUnit.getByAcronym("ml");
        assertEquals(MeasureUnit.ML, ml);

        final MeasureUnit kg = MeasureUnit.getByAcronym("Kg");
        assertEquals(MeasureUnit.KG, kg);

        final MeasureUnit naoexiste = MeasureUnit.getByAcronym("naoexiste");
        assertNull(naoexiste);

        final MeasureUnit vazio = MeasureUnit.getByAcronym("");
        assertNull(naoexiste);

    }





}
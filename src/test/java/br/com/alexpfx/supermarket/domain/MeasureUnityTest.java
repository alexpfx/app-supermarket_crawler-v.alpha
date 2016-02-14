package br.com.alexpfx.supermarket.domain;

import org.junit.Assert;
import org.junit.Test;

import static br.com.alexpfx.supermarket.domain.MeasureUnit.Patterns.QUANTITY_UNIT;

/**
 * Created by alexandre on 13/02/2016.
 */
public class MeasureUnityTest {


    @Test
    public void quantity() {
        String testString = "abc1kgabc1l";
        String fg = QUANTITY_UNIT.getQuantity(testString);
        Assert.assertEquals("1", fg);
    }


    @Test
    public void unity() {
        String testString = "abc1kgabc1l";
        String fg = QUANTITY_UNIT.getUnity(testString);
        Assert.assertEquals("kg", fg);
    }

    @Test
    public void unityUpperCase() {
        String testString = "abc1KGabc1l";
        String fg = QUANTITY_UNIT.getUnity(testString);
        Assert.assertEquals("kg", fg);
    }


    @Test
    public void unityNoOccurrence() {
        String testString = "fasfasfasfaf";
        String fg = QUANTITY_UNIT.getQuantity(testString);
        Assert.assertEquals("", fg);
    }

    @Test
    public void unityEmptyString() {
        String testString = "";
        String fg = QUANTITY_UNIT.getQuantity(testString);
        Assert.assertEquals("", fg);
    }

    /* If more than one matches, return the first*/
    @Test
    public void unityMoreThanOne() {
        String testString = "abc5mlabc5kg";
        String fg = QUANTITY_UNIT.getUnity(testString);
        Assert.assertEquals("ml", fg);
    }

    /* If more than one matches, return the first*/
    @Test
    public void quantityMoreThanOne() {
        String testString = "abcm5mlabc1kg";
        String fg = QUANTITY_UNIT.getQuantity(testString);
        Assert.assertEquals("5", fg);
    }

}
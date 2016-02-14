package br.com.alexpfx.supermarket.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alexandre on 13/02/2016.
 */
public class RegexTest {


    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void regexTest() {

        String ex = "Água de Coco KERO COCO 1mgÁgua 2mgde Coco KERO COCO1l2l1KG145KG";
        String regex = MeasureUnit.getRegex();
        Pattern pattern = Pattern.compile("\\d{1,5}("+regex+")", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(ex);

        while (matcher.find()){
            System.out.println(matcher.group());
        }


    }
}
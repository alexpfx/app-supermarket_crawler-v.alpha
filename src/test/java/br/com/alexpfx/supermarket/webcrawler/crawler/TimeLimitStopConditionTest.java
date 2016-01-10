package br.com.alexpfx.supermarket.webcrawler.crawler;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by alexandre on 10/01/2016.
 */
public class TimeLimitStopConditionTest {

    TimeLimitStopCondition timeLimitStopCondition;



    @Test
    public void testIsReached() throws Exception {
        timeLimitStopCondition = new TimeLimitStopCondition(10L, TimeUnit.SECONDS);
        timeLimitStopCondition.init(System.currentTimeMillis(), "", 0, 0);
        while (!timeLimitStopCondition.isReached()) {

        }
        assertEquals(timeLimitStopCondition.elapsed(TimeUnit.SECONDS), 10L);
    }
}
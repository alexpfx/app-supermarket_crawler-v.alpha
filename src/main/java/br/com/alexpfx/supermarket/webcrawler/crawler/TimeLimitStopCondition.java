package br.com.alexpfx.supermarket.webcrawler.crawler;

import java.util.concurrent.TimeUnit;

/**
 * Created by alexandre on 10/01/2016.
 */
public class TimeLimitStopCondition implements StopCondition {
    private long startTime;
    private long timeLimit;
    private TimeUnit timeUnit;
    private long elapsed = 0;

    public TimeLimitStopCondition(long timeLimit, TimeUnit timeUnit) {
        this.timeLimit = timeLimit;
        this.timeUnit = timeUnit;
    }

    @Override
    public void init(long startTime, String link, int actual, int size) {
        this.startTime = startTime;
    }

    @Override
    public boolean isReached() {
        elapsed = System.currentTimeMillis() - startTime;
        return (elapsed > timeUnit.toMillis(timeLimit));
    }

    public long elapsed(TimeUnit timeUnit) {
        return timeUnit.convert(elapsed, TimeUnit.MILLISECONDS);

    }
}

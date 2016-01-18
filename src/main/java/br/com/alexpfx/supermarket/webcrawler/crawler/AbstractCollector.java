package br.com.alexpfx.supermarket.webcrawler.crawler;

import com.google.common.base.Preconditions;
import com.jaunt.UserAgent;

import java.util.List;

/**
 * This class was created to avoid subclass to have to implement setters for collectorRule,collectorListener and userAgent and to
 * ensure userAgent is not null.
 */
public abstract class AbstractCollector<T> implements Collector<T> {


    // fields are to be used ("exclusively") in subclasses, so I use protected modifier.
    protected CollectorRule<T> collectorRule = (CollectorRule<T>) CollectorRule.EMPTY;
    protected UserAgent userAgent;

    @Override
    public final void setUserAgent(UserAgent userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public final List<T> collect(List<String> urls) {
        Preconditions.checkNotNull(userAgent);
        return doCollect(urls);
    }

    protected abstract List<T> doCollect(List<String> urls);
}

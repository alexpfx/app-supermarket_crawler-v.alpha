package br.com.alexpfx.supermarket.webcrawler.crawler;

/**
 * Created by alexandre on 10/01/2016.
 */
public interface StopCondition {
    void init(long startTime, String link, int actual, int size);

    boolean isReached();

    public StopCondition EMTPY = new StopCondition() {
        @Override
        public void init(long startTime, String link, int actual, int size) {

        }

        @Override
        public boolean isReached() {
            return false;
        }
    };
}

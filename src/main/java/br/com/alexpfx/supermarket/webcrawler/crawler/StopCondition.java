package br.com.alexpfx.supermarket.webcrawler.crawler;

/**
 * Created by alexandre on 10/01/2016.
 */
public interface StopCondition {
    void init(long time, String link, int actual, long startTime);
    boolean evaluate ();
    public StopCondition EMTPY = new StopCondition() {
        @Override
        public void init(long time, String link, int actual, long startTime) {

        }

        @Override
        public boolean evaluate() {
            return false;
        }
    };
}

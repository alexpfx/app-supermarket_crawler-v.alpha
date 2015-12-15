package br.com.alexpfx.supermarket.crawler.model.database;

import br.com.alexpfx.supermarket.crawler.model.domain.Keywords;

/**
 * Created by alexandre on 13/12/2015.
 */
public class KeywordRepositoryMysql implements KeywordRepository {
    @Override
    public double matchRate(Keywords keywords) {

        return 0;
    }
}

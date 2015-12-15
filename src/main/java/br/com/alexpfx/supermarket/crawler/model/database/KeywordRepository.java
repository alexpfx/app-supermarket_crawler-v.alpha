package br.com.alexpfx.supermarket.crawler.model.database;

import br.com.alexpfx.supermarket.crawler.model.domain.Keywords;

/**
 * Created by alexandre on 13/12/2015.
 */
public interface KeywordRepository {
    double matchRate(Keywords keywords);
}

package br.com.alexpfx.supermarket.crawler.model.database;

/**
 * Created by alexandre on 21/12/2015.
 */
public interface MysqlCommonDao extends Dao{
    Integer getLastInsertId ();
}

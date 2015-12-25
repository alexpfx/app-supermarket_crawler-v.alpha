package br.com.alexpfx.supermarket.crawler.model.database;

import com.google.common.base.Preconditions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alexandre on 21/12/2015.
 */
public class MysqlCommonDaoImpl implements MysqlCommonDao {

    private QueryManager queryManager;

    public MysqlCommonDaoImpl(QueryManager queryManager) {
        this.queryManager = queryManager;
    }

    @Override
    public Integer getLastInsertId() {
        Preconditions.checkNotNull(queryManager);
        return queryManager.executeQuery("common.last_insert_id").nextInteger (1);
    }
}

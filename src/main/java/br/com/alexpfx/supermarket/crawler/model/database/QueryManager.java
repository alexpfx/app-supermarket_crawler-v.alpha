package br.com.alexpfx.supermarket.crawler.model.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by alexandre on 20/12/2015.
 */
public interface QueryManager {

    ResultSetManager executeQuery(String queryName, QueryParams params);
    void executeUpdate(String queryName, QueryParams params);

    ResultSetManager executeQuery(String queryName);
    void executeUpdate(String queryName);


}

package br.com.alexpfx.supermarket.crawler.model.database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alexandre on 21/12/2015.
 */
public class ResultSetManagerImpl implements ResultSetManager {
    private ResultSet resultSet;

    public ResultSetManagerImpl(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    @Override
    public Boolean nextBoolean(int index) {
        try {
            resultSet.next();
            return resultSet.getBoolean(index);

        } catch (SQLException e) {
            throw new RuntimeException("sql error");
        }


    }

    @Override
    public Integer nextInteger(int index) {
        try {
            resultSet.next();
            return resultSet.getInt(index);
        } catch (SQLException e) {
            throw new RuntimeException("sql error");
        }
    }
}

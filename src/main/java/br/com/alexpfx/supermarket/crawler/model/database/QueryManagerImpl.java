package br.com.alexpfx.supermarket.crawler.model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alexandre on 20/12/2015.
 */
public class QueryManagerImpl implements QueryManager {

    private Connection connection;
    private JsonQueryFile jsonQueryFile;

    private QueryManagerImpl(Connection connection, JsonQueryFile jsonQueryFile) {
        this.connection = connection;
        this.jsonQueryFile = jsonQueryFile;
    }

    public static QueryManagerImpl of(Connection connection, JsonQueryFile jsonQueryFile) {
        return new QueryManagerImpl(connection, jsonQueryFile);
    }

    @Override
    public ResultSet executeQuery(String queryName, QueryParams params) {
        String query = jsonQueryFile.get(queryName);
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            setParams(params, ps);
            return ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setParams(QueryParams params, PreparedStatement ps) {
        params.forEach(e -> {
            try {
                ps.setObject(e.getKey(), e.getValue());
                System.out.println(e);
            } catch (SQLException e1) {
                throw new RuntimeException("Erro ao setar parametros", e1);
            }
        });
    }


    @Override
    public void executeUpdate(String queryName, QueryParams params) {
        String query = jsonQueryFile.get(queryName);
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            setParams(params, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

package br.com.alexpfx.supermarket.crawler.model.database;

import br.com.alexpfx.supermarket.crawler.model.domain.Product;

import java.sql.*;

/**
 * Created by alexandre on 13/12/2015.
 */
public class ProductRepositoryMysql implements ProductRepository {

    private Connection connection;

    public ProductRepositoryMysql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Product product) {
        try (PreparedStatement ps = connection.prepareStatement("insert into produtos (descricao, codigo_ean) values (?,?)");) {
            ps.setString(1, product.getDescription());
            ps.setString(2, product.getBarCode().getCode());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean exists(Product product) {
        try (PreparedStatement ps = connection.prepareStatement("select count(*) from produtos where codigo_ean = ?")) {
            ps.setString(1, product.getBarCode().getCode());
            ResultSet r = ps.executeQuery();
            r.next();
            return r.getBoolean(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
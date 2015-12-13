package br.com.alexpfx.supermarket.crawler.model.database;

import br.com.alexpfx.supermarket.crawler.model.domain.Keywords;
import br.com.alexpfx.supermarket.crawler.model.domain.Product;

import java.sql.*;
import java.util.Iterator;

/**
 * Created by alexandre on 13/12/2015.
 */
public class ProductRepositoryMysql implements ProductRepository {

    private Connection connection;

    public ProductRepositoryMysql(Connection connection) {
        this.connection = connection;
    }


    private int last_index_id() {
        try (Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery("select last_insert_id()");
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void save(Product product) {
        try (PreparedStatement ps = connection.prepareStatement("insert into produtos (descricao, codigo_ean) values (?,?)");) {
            ps.setString(1, product.getDescription());
            ps.setString(2, product.getBarCode().getCode());
            ps.executeUpdate();
            int lastid = last_index_id();
            Keywords keywords = product.getKeywords();
            save(keywords, lastid);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void save(Keywords keywords, int idProduto) {
        try (PreparedStatement ps = connection.prepareStatement("insert into keywords (keyword, id_produto) values (?, ?)");) {
            Iterator<String> iterator = keywords.getIterator();
            while (iterator.hasNext()) {
                String w = iterator.next();
                ps.setString(1, w);
                ps.setInt(2, idProduto);
                ps.executeUpdate();
            }
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
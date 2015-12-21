package br.com.alexpfx.supermarket.crawler.model.database;

import br.com.alexpfx.supermarket.crawler.model.domain.Product;
import com.google.common.base.Preconditions;
import org.apache.http.util.Asserts;

import java.sql.*;
import java.sql.Date;

/**
 * Created by alexandre on 13/12/2015.
 */
public class ProductDaoMysql implements ProductDao {


    public static final int TIPO_EAN = 1;
    private Connection connection;

    public ProductDaoMysql(Connection connection, JsonQueryFileImpl jsonQueryFile) {

        this.connection = connection;
    }

    private int last_index_id() {
        return 0;
    }

    @Override
    public void save(Product product) {
        QueryManager qm = QueryManagerImpl.of(connection, new JsonQueryFileImpl("products.json"));
        qm.executeUpdate("insert", new QueryParamsImpl(product.getDescription(), product.getUrl(), new Date(System.currentTimeMillis())));
        QueryManager qmLastId = QueryManagerImpl.of(connection, new JsonQueryFileImpl("common.json"));
        ResultSet rs = qmLastId.executeQuery("last_insert_id", QueryParams.EMPTY);
        Integer lastId = null;
        try {
            if (rs.next()){
                lastId = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Preconditions.checkNotNull(lastId);
        qm.executeUpdate("atribuir_identificador", new QueryParamsImpl(TIPO_EAN, lastId));
    }

    @Override
    public boolean exists(Product product) {
        return false;
    }

    @Override
    public void delete(Product product) {

    }


}

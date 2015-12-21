package br.com.alexpfx.supermarket.crawler.model.database;

import br.com.alexpfx.supermarket.crawler.model.domain.Product;
import com.google.common.base.Preconditions;
import org.apache.http.util.Asserts;
import org.apache.xmlbeans.impl.store.Query;

import java.sql.*;
import java.sql.Date;

/**
 * Created by alexandre on 13/12/2015.
 */
public class ProductDaoMysql implements ProductDao {


    public static final int TIPO_EAN = 1;

    private QueryManager queryManager;
    private MysqlCommonDao mysqlCommonDao;

    @Override
    public void setMysqlCommonDao(MysqlCommonDao mysqlCommonDao) {
        this.mysqlCommonDao = mysqlCommonDao;
    }

    @Override
    public void save(Product product) {
        Preconditions.checkNotNull(queryManager);
        queryManager.executeUpdate("products.insert", new QueryParamsImpl(product.getDescription(), product.getUrl(), new Date(System.currentTimeMillis())));
        Integer lastId = mysqlCommonDao.getLastInsertId();
        Preconditions.checkNotNull(lastId);
        queryManager.executeUpdate("products.atribuir_identificador", new QueryParamsImpl(TIPO_EAN, lastId));
    }

    @Override
    public boolean exists(Product product) {
        return queryManager.executeQuery("products.exists", new QueryParamsImpl(product.getProductId(), product.getIdentityType())).nextBoolean(1);
    }

    @Override
    public void delete(Product product) {

    }


    @Override
    public void setQueryManager(QueryManager manager) {
        this.queryManager = manager;
    }
}

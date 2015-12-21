package br.com.alexpfx.supermarket.crawler.model.database;

import br.com.alexpfx.supermarket.crawler.model.domain.*;
import org.junit.*;

import java.sql.SQLException;

/**
 * Created by alexandre on 13/12/2015.
 */
public class ProductDaoMysqlTest extends BaseDatabaseTest {

    ProductDao dao;


    @Before
    public void setUp() throws ClassNotFoundException {
        dao = new ProductDaoMysql(QueryManagerImpl.of(getConnection(),new JsonQueryFileImpl("products.json")));
    }

    @After
    public void tearDown() throws SQLException {
        dao = null;
        closeConnection();
    }

    @Test
    public void testSave() throws Exception {
        Keywords k = Keywords.of("um dois tres quatro cinco seis sete oito nove dez onze");
        ProductBuilder b = new ProductBuilder();
        String code = "00000000000";
        Product produto = b.barCode(BarCode.of(code, BarCodeType.EAN)).description("produto").url("http").keywords(k).createProduct();
        dao.save(produto);
        Assert.assertTrue(dao.exists(produto));
        dao.delete(produto);


    }
    public void testExists() throws Exception {

    }

    public void testSave1() throws Exception {

    }

    public void testExists1() throws Exception {

    }

}
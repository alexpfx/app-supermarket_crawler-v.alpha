package br.com.alexpfx.supermarket.crawler.model.database;

import br.com.alexpfx.supermarket.crawler.model.domain.*;
import org.junit.*;

import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by alexandre on 13/12/2015.
 */
public class ProductRepositoryMysqlTest extends BaseDatabaseTest {

    ProductRepository repository;


    @Before
    public void setUp() throws ClassNotFoundException {
        repository = new ProductRepositoryMysql(getConnection());
    }

    @After
    public void tearDown() throws SQLException {
        repository = null;
        closeConnection();
    }

    @Test
    public void testSave() throws Exception {
        Keywords k = Keywords.of("um dois tres quatro cinco seis sete oito nove dez onze");
        ProductBuilder b = new ProductBuilder();
        String code = "00000000000";
        Product produto = b.barCode(BarCode.of(code, BarCodeType.EAN)).description("produto").keywords(k).createProduct();
        repository.save(produto);
        Assert.assertTrue(repository.exists(produto));

        repository.delete(produto);


    }

    @Test
    public void testExists() throws Exception {

    }
}
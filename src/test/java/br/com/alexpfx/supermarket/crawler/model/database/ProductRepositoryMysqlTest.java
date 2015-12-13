package br.com.alexpfx.supermarket.crawler.model.database;

import br.com.alexpfx.supermarket.crawler.model.domain.BarCode;
import br.com.alexpfx.supermarket.crawler.model.domain.BarCodeType;
import br.com.alexpfx.supermarket.crawler.model.domain.Keywords;
import br.com.alexpfx.supermarket.crawler.model.domain.Product;
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
        Keywords k = Keywords.ofPhrase("um dois tres quatro cinco seis sete oito nove dez onze");
        Product produto = Product.of(BarCode.of("000000000000", BarCodeType.EAN), "produto", k);
        repository.save(produto);
        Assert.assertTrue(repository.exists(produto));
        System.out.println();
    }

    @Test
    public void testExists() throws Exception {

    }
}
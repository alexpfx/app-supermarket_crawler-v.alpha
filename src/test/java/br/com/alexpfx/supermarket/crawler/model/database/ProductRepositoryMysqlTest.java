package br.com.alexpfx.supermarket.crawler.model.database;

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


    }

    @Test
    public void testExists() throws Exception {
        boolean exists = repository.exists("7891010037352");
        assertTrue(exists);
    }
}
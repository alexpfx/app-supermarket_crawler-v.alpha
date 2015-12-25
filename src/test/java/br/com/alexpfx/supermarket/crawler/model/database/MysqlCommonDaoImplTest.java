package br.com.alexpfx.supermarket.crawler.model.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.notification.RunListener;

import static org.junit.Assert.*;

/**
 * Created by alexandre on 21/12/2015.
 */
public class MysqlCommonDaoImplTest extends BaseDatabaseTest{
    MysqlCommonDao dao;

    @Before
    public void setUp(){
        dao = new MysqlCommonDaoImpl(new QueryManagerImpl(getConnection(),new JsonQueryFileImpl("common.json")));
    }
    @Test
    public void testGetLastInsertId() throws Exception {
        Integer lastInsertId = dao.getLastInsertId();
        assertNull(lastInsertId);
    }

    @After
    public void tearDown (){
        dao = null;

    }
}
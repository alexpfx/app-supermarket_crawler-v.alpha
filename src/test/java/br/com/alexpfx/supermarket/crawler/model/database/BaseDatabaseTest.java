package br.com.alexpfx.supermarket.crawler.model.database;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by alexandre on 13/12/2015.
 */
public abstract class BaseDatabaseTest {
    public static final String URL = "jdbc:mysql://localhost/smket";
    private Connection connection;

    void setupConnection()  {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, "alex", "alex00");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public Connection getConnection() {
        if (connection == null)
            setupConnection();
        return connection;
    }

    public void beforeClass() throws Exception {
        setupConnection();
    }

    public void afterClass() throws Exception {
        closeConnection();
    }

}

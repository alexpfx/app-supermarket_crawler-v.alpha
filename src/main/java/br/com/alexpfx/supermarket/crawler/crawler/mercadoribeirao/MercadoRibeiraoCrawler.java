package br.com.alexpfx.supermarket.crawler.crawler.mercadoribeirao;

import br.com.alexpfx.supermarket.crawler.crawler.Crawler;
import br.com.alexpfx.supermarket.crawler.crawler.CrawlerListener;
import br.com.alexpfx.supermarket.crawler.model.database.Crud;
import br.com.alexpfx.supermarket.crawler.model.database.ProductRepository;
import br.com.alexpfx.supermarket.crawler.model.database.ProductRepositoryMysql;
import br.com.alexpfx.supermarket.crawler.model.domain.BarCode;
import br.com.alexpfx.supermarket.crawler.model.domain.BarCodeType;
import br.com.alexpfx.supermarket.crawler.model.domain.Keywords;
import br.com.alexpfx.supermarket.crawler.model.domain.Product;
import br.com.alexpfx.supermarket.crawler.model.to.ProductInfoTO;
import com.firebase.client.Firebase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by alexandre on 12/12/2015.
 */
public class MercadoRibeiraoCrawler extends Crawler {
    public static final String REF_URL = "https://smket.firebaseio.com/";
    public static final String URL = "jdbc:mysql://localhost/smket";
    private Crud<Map.Entry> productInfoCrud = new Crud<>(new Firebase(REF_URL));
    private ProductRepository productRepository;

    @Override
    public void init() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, "alex", "alex00");
            System.out.println(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        productRepository = new ProductRepositoryMysql(connection);

        setListener(new CrawlerListener() {
            @Override
            public void onProductVisit(ProductInfoTO productInfo) throws InterruptedException {
                Product product = Product.of(BarCode.of(productInfo.getId(), BarCodeType.EAN), productInfo.getDescription(), Keywords.of(productInfo.getDescription()));
                if (!productRepository.exists(product)) {
                    productRepository.save(product);
                }
            }
        });

        setCrawlerModel(new MercadoRibeiraoCrawlerModel());
    }
}

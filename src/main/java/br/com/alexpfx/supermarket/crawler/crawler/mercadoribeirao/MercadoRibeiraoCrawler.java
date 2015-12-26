package br.com.alexpfx.supermarket.crawler.crawler.mercadoribeirao;

import br.com.alexpfx.supermarket.crawler.crawler.Crawler;
import br.com.alexpfx.supermarket.crawler.crawler.CrawlerListener;
import br.com.alexpfx.supermarket.crawler.model.database.Crud;
import br.com.alexpfx.supermarket.crawler.model.database.ProductDao;
import br.com.alexpfx.supermarket.crawler.model.domain.Product;
import br.com.alexpfx.supermarket.crawler.model.to.ProductInfoTO;
import com.firebase.client.Firebase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * Created by alexandre on 12/12/2015.
 */
public class MercadoRibeiraoCrawler extends Crawler {


    public static final String REF_URL = "https://smket.firebaseio.com/";
    public static final String URL = "jdbc:mysql://localhost/smket";
    private Crud<Map.Entry> productInfoCrud = new Crud<>(new Firebase(REF_URL));
    private ProductDao productDao;

    @Override
    public void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("smkt");
        EntityManager entityManager = factory.createEntityManager();
        Query q = entityManager.createQuery("select p from Product p");
        List resultList = q.getResultList();


        entityManager.getTransaction().begin();
        Product p = new Product();
        p.setDescription("oiu");
        p.setUrl("fafas");
        entityManager.persist(p);
        entityManager.getTransaction().commit();

        entityManager.close();


        //TODO separar
        setListener(new CrawlerListener() {
            @Override
            public void onProductVisit(ProductInfoTO productInfo) throws InterruptedException {

//                ProductBuilder builder = new ProductBuilder();
//                builder.description(productInfo.getDescription()).keywords(Keywords.of(productInfo.getDescription()));
////                if (productInfo.isValidEan()) {
////                    builder.barCode(ProductIdentityEan.of(productInfo.getId(), ProductIdentityType.EAN));
////                } else {
////                    builder.alternativeId(productInfo.getId());
////                }
//                builder.url(productInfo.getUrl());
//
//                Product product = builder.createProduct();
//                System.out.println(product);

//                if (!productDao.exists(product)) {
//                    productDao.save(product);
//                }
            }
        });

        setCrawlerModel(new MercadoRibeiraoCrawlerModel());
    }
}

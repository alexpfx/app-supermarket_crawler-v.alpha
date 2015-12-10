package br.com.alexpfx.supermarket.crawler;

import br.com.alexpfx.supermarket.crawler.controller.ControllerConfig;
import br.com.alexpfx.supermarket.crawler.controller.CrawlerStarter;
import br.com.alexpfx.supermarket.crawler.controller.angeloni.AngeloniControllerConfig;
import br.com.alexpfx.supermarket.crawler.model.Crud;
import br.com.alexpfx.supermarket.crawler.model.to.ProductInfoTO;
import com.firebase.client.*;
import org.apache.log4j.BasicConfigurator;

/**
 * Created by alexandre on 06/12/2015.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        BasicConfigurator.configure();
        new Main().save();

//        c.start();
    }

    public void save() throws InterruptedException {
        Firebase.setDefaultConfig(new Config());
        final Firebase refri = new Firebase("https://supermarketcrawler.firebaseIO.com/");
        Crud<ProductInfoTO> crud = new Crud<>(refri);

        ControllerConfig config = new AngeloniControllerConfig();
        CrawlerStarter starter = new CrawlerStarter(config);


        refri.orderByChild("/descgription").endAt("cerveja").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        Firebase ref = new Firebase("https://docs-examples.firebaseio.com/web/saving-data/fireblog/posts");
// Attach an listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

        while (true){
            Thread.sleep(1000);
        }
    }
}

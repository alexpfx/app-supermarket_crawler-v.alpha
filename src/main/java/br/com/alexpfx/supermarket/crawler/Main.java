package br.com.alexpfx.supermarket.crawler;

import com.firebase.client.*;
import org.apache.log4j.BasicConfigurator;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

/**
 * Created by alexandre on 06/12/2015.
 */
public class Main implements Firebase.AuthResultHandler, ValueEventListener {
    public static void main(String[] args) throws InterruptedException {

        BasicConfigurator.configure();
        new Main().save();

//        c.start();
    }

    public void save () throws InterruptedException {
        final Semaphore semaphore = new Semaphore(0);


        Firebase.setDefaultConfig(new Config());
        final Firebase ref = new Firebase("https://supermarketcrawler.firebaseIO.com/");
        ref.authAnonymously(this);
        ref.addValueEventListener(this);


        ref.child("/message").setValue("ms");
        ref.child("/message").addValueEventListener(this);

        Map<String, Boolean> test = new HashMap<String, Boolean>();
        test.put("Test", true);
        ref.setValue(test, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError error, Firebase ref) {
                semaphore.release();
            }
        });
        ref.setValue(test);
        semaphore.acquire();

    }

    public void onAuthenticated(AuthData authData) {
        System.out.println(authData);
    }

    public void onAuthenticationError(FirebaseError firebaseError) {
        System.out.println(firebaseError);
    }

    public void onDataChange(DataSnapshot dataSnapshot) {
        System.out.println(dataSnapshot.getValue());
    }

    public void onCancelled(FirebaseError firebaseError) {

    }
}

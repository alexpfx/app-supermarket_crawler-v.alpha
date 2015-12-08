package br.com.alexpfx.supermarket.crawler.model;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.annotations.NotNull;

import java.util.concurrent.Semaphore;

/**
 * Created by alexandre on 08/12/2015.
 */
public class Crud <T> implements Repository <T>{


    private Firebase ref;


    public Crud(Firebase ref) {
        this.ref = ref;
    }

    @Override
    public void save(String key, T value) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(0);
        ref.setValue(value, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                semaphore.release();
            }
        });
        semaphore.acquire();
    }
}

package br.com.alexpfx.supermarket.crawler.model.database;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.concurrent.CountDownLatch;

/**
 * Created by alexandre on 08/12/2015.
 */
public class Crud<T> implements Repository<T> {
    private Firebase ref;
    public Crud(Firebase ref) {
        this.ref = ref;
    }

    @Override
    public void save(String path, T value) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        Firebase child = ref.child(path);
        child.setValue(value, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                countDownLatch.countDown();
            }
        });
        countDownLatch.wait();
    }
}

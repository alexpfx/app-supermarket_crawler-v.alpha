package br.com.alexpfx.supermarket.crawler.model.database;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

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
        final Semaphore semaphore = new Semaphore(0);
        Firebase child = ref.child(path);

        child.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                semaphore.release();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        semaphore.acquire();
        child.setValue(value);
    }
}

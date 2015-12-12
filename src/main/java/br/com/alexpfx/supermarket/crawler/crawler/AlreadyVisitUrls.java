package br.com.alexpfx.supermarket.crawler.crawler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by alexandre on 07/12/2015.
 */
public class AlreadyVisitUrls {

    private List<String> urls = new CopyOnWriteArrayList<String>();


    public void add(String url) {
        urls.add(url.trim());
    }

    public boolean contains(String url){
        return urls.contains(url.trim());
    }

    public int size (){
        return urls.size();
    }

}

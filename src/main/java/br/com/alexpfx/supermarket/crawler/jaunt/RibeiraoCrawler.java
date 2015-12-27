package br.com.alexpfx.supermarket.crawler.jaunt;

import com.jaunt.*;

/**
 * Created by alexandre on 27/12/2015.
 */
public class RibeiraoCrawler implements CrawlerImp{

    private UserAgent userAgent;
    private String startUrl;

    public RibeiraoCrawler(UserAgent userAgent, String startUrl) {
        this.userAgent = userAgent;
        this.startUrl = startUrl;
    }

    public void run (){

        try {
            userAgent.visit(startUrl);
            Elements subMenu = userAgent.doc.findEvery("<a class=new_sub_menu>").findEvery("<a>");
            subMenu.forEach(e -> {
                try {
                    String href = e.getAt("href");
                    userAgent.visit(href);
                    Element container = userAgent.doc.findFirst("<div class=item-meta-container>");
                    Element itemName = container.findFirst("<h3 class=item-name>");
                    Element first1 = itemName.findFirst("<a>");
                    System.out.println(first1.innerHTML());

                } catch (NotFound notFound) {

                } catch (ResponseException e1) {

                }

            });



        } catch (ResponseException e) {


        }


    }


}

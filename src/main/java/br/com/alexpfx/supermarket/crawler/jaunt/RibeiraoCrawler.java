package br.com.alexpfx.supermarket.crawler.jaunt;

import br.com.alexpfx.supermarket.crawler.model.domain.Product;
import com.jaunt.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 27/12/2015.
 */
public class RibeiraoCrawler extends AbstractCrawler {

    public RibeiraoCrawler(UserAgentFactory userAgent) {
        super(userAgent.createUserAgent(), "https://www.mercadoribeirao.com.br/");
    }

    @Override
    List<String> extractSubPages(Document document) {
        List<String> list = new ArrayList<>();
        Elements submenu = document.findEvery("<a class=new_sub_menu>");
        submenu.findEvery("<a>").forEach(element -> {
            try {
                String href = element.getAt("href");
                list.add(href);
            } catch (NotFound notFound) {
                notFound.printStackTrace();
            }
        });
        return list;
    }

    @Override
    protected List<Product> extractProducts(Document doc) {
        Elements itemList = doc.findEach("<div class=item-meta-container>");
        itemList.forEach(item -> {
            String name = extractName(item);
            String code = extractCode(item);
            String url = extractProductUrl(item);
        });
        return null;
    }

    private String extractProductUrl(Element item) {
        try {
            Element first = item.findFirst("<div class=ratings-container>").findFirst("<a>");
            return first.getAt("href");
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        }
        return null;
    }


    private String extractCode(Element item_meta_container) {
        try {
            Element first = item_meta_container.findFirst("<div class=ratings-container>").findFirst("<a>");
            return first.getText();
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        }
        return null;
    }

    private String extractName(Element item_meta_container) {
        try {
            Element first = item_meta_container.findFirst("<h3 class=item-name>").findFirst("<a>");
            return first.getText();
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        }
        return null;
    }

}

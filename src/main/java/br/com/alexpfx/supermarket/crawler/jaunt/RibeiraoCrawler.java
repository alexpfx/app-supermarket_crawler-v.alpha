package br.com.alexpfx.supermarket.crawler.jaunt;

import br.com.alexpfx.supermarket.crawler.model.domain.Product;
import br.com.alexpfx.supermarket.crawler.model.domain.barcode.BarCode;
import br.com.alexpfx.supermarket.crawler.model.domain.barcode.Ean13;
import br.com.alexpfx.supermarket.crawler.model.domain.barcode.Ean13Factory;
import br.com.alexpfx.supermarket.crawler.model.exception.InvalidBarCodeException;
import com.jaunt.Document;
import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.NotFound;

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
    protected List<String> extractSubPages(Document document) {
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
        List<Product> products = new ArrayList<>();
        itemList.forEach(item -> {
            String name = extractName(item);
            String code = extractCode(item);
            String url = extractProductUrl(item);
            Product p = new Product();
            p.setDescription(name);
            p.setUrl(url);
            try {
                //TODO refatorar
                BarCode ean = new Ean13Factory().create(code);
                p.setEan((Ean13) ean);
            } catch (InvalidBarCodeException e) {
                logInvalidCode(e, code);
            }
        });
        return products;
    }

    private void logInvalidCode(InvalidBarCodeException e, String code) {
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
            return first.getText().trim();
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

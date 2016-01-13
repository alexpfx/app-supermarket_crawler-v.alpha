package br.com.alexpfx.supermarket.webcrawler.crawler.impl;

import br.com.alexpfx.supermarket.webcrawler.crawler.AbstractCrawler;
import br.com.alexpfx.supermarket.webcrawler.exception.InvalidBarCodeException;
import br.com.alexpfx.supermarket.webcrawler.factory.UserAgentFactory;
import br.com.alexpfx.supermarket.webcrawler.to.ProdutoSuperMercadoTOBuilder;
import br.com.alexpfx.supermarket.webcrawler.to.TransferObject;
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
    protected List<TransferObject> extract(Document doc) {
        Elements itemList = doc.findEach("<div class=item-meta-container>");
        List<TransferObject> products = new ArrayList<>();
        itemList.forEach(item -> {
            String name = extractName(item);
            String code = extractCode(item);
            String url = extractProductUrl(item);

            ProdutoSuperMercadoTOBuilder builder = new ProdutoSuperMercadoTOBuilder();
            TransferObject to = builder.descricao(name).url(url).code(code).fabricante(extractFabricante()).create();
            products.add(to);
        });
        return products;
    }

    private String extractFabricante() {
        return "fabricante";
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

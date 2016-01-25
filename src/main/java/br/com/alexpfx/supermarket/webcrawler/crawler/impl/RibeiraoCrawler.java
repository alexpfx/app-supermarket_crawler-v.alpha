package br.com.alexpfx.supermarket.webcrawler.crawler.impl;

import br.com.alexpfx.supermarket.webcrawler.crawler.AbstractCrawler;
import br.com.alexpfx.supermarket.webcrawler.crawler.CollectorRule;
import br.com.alexpfx.supermarket.webcrawler.crawler.ItemsCollector;
import br.com.alexpfx.supermarket.webcrawler.crawler.UrlsCollector;
import br.com.alexpfx.supermarket.webcrawler.to.ProdutoSuperMercadoTOBuilder;
import br.com.alexpfx.supermarket.webcrawler.to.TransferObject;
import com.jaunt.UserAgent;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by alexandre on 27/12/2015.
 */
public class RibeiraoCrawler extends AbstractCrawler {

    private static final CollectorRule VISITOR_RULE = doc -> {
        List<String> list = new ArrayList<>();
        Elements submenu = doc.select("a.new_sub_menu");
        submenu.forEach(element1 -> {
            String href = element1.attr("abs:href");
            list.add(href.concat("&pageNum=VER-TUDO"));
        });
        return list;
    };

    private static final CollectorRule<TransferObject> ITEM_RULE = doc -> {
        Elements itemList = doc.select("div.item-meta-container");
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
    };

    public RibeiraoCrawler(UserAgent userAgent) {
        super(new UrlsCollector(VISITOR_RULE), new ItemsCollector(ITEM_RULE), Collections.singletonList("https://www.mercadoribeirao.com.br/"));
    }


    private static String extractFabricante() {
        return "fabricante";
    }


    private static String extractProductUrl(Element item) {
        Elements select = item.select("h3.item-name");
        return select.attr("href");
    }


    private static String extractCode(Element item_meta_container) {
        return "product code";
    }

    private static String extractName(Element item_meta_container) {
        Elements select = item_meta_container.select("h3.item-name");
        return select.text();
    }

}

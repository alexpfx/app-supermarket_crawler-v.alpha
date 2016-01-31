package br.com.alexpfx.supermarket.webcrawler.crawler.collector.rules;

import br.com.alexpfx.supermarket.webcrawler.to.ProdutoSuperMercadoTO;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by alexandre on 25/01/2016.
 */
public class RibeiraoCollectorRule extends ProductCollectorFromPageListAdapter<ProdutoSuperMercadoTO> {


    @Override
    public Elements extractItemList(Document doc) {
        return doc.select("div.item-meta-container");
    }

    @Override
    protected String extractCodigo(Element p) {
        Element a = p.select("ratings-container").select("a").first();
        return a.text();
    }

    @Override
    protected String extractDescricao(Element p) {
        Elements select = p.select("h3.item-name");
        return select.text();
    }

    @Override
    protected String extractUrl(Element p) {
        Element a = p.select("h3.item-name").select("a").first();
        return a.attr("abs:href");

    }


}

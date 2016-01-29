package br.com.alexpfx.supermarket.webcrawler.crawler.collector.rules;

import br.com.alexpfx.supermarket.webcrawler.crawler.collector.CollectorRule;
import br.com.alexpfx.supermarket.webcrawler.to.ProdutoSuperMercadoTO;
import br.com.alexpfx.supermarket.webcrawler.to.TransferObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * Created by alexandre on 25/01/2016.
 */
public class AngeloniCollectorRule <T> extends ProductCollectorFromPageListAdapter <ProdutoSuperMercadoTO> {

    @Override
    public Elements extractItemList(Document doc) {
        return doc.select("ul.lstProd ");
    }

    @Override
    protected String extractCodigo(Element p) {
        return p.select("cod").text();
    }

    @Override
    protected String extractUrl(Element p) {
        return p.select("").toString();
    }

    @Override
    protected String extractQuantidade(Element p) {
        return super.extractQuantidade(p);
    }




}

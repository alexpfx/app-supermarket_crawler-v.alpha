package br.com.alexpfx.supermarket.webcrawler.crawler.collector.rules;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by alexandre on 25/01/2016.
 */
public class AngeloniExtractionRules extends ProductExtractorFromPageListAdapter {

    @Override
    protected String extractDescricao(Element p) {
        return "";


    }

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
        return "";
    }

    @Override
    protected String extractQuantidade(Element p) {
        return super.extractQuantidade(p);
    }


}

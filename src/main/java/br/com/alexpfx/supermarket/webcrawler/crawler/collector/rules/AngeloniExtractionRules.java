package br.com.alexpfx.supermarket.webcrawler.crawler.collector.rules;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by alexandre on 25/01/2016.
 */
public class AngeloniExtractionRules extends ProductExtractorFromPageListAdapter {


    @Override
    public Elements extractItemList(Document doc) {
        doc.setBaseUri("http://www.angeloni.com.br/");
        return doc.select("ul.lstProd li");
    }

    @Override
    protected String extractCodigo(Element p) {
        return p.select("span.cod").text();
    }

    @Override
    protected String extractDescricao(Element p) {
        String text = p.select("span.descr").text();
        return text;
    }


    @Override
    protected String extractUrl(Element p) {
        String text = p.select("span.descr a").attr("abs:href");
        return text;
    }

    @Override
    protected String extractQuantidade(Element p) {
        return super.extractQuantidade(p);
    }


}

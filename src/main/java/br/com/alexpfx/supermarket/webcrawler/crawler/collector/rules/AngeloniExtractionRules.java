package br.com.alexpfx.supermarket.webcrawler.crawler.collector.rules;

import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.CrawlerAPI;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by alexandre on 25/01/2016.
 */
public class AngeloniExtractionRules extends ProductExtractorFromPageListAdapter {


    public AngeloniExtractionRules(
            CrawlerAPI crawlerAPI) {
        super(crawlerAPI);
    }

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
        return p.select("span.descr").text().trim();

    }


    @Override
    protected String extractUrl(Element p) {
        return p.select("span.descr a").attr("abs:href").trim();

    }

    @Override
    protected String extractQuantidade(Element p) {
        return super.extractQuantidade(p);
    }


}

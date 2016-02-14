package br.com.alexpfx.supermarket.webcrawler.crawler.collector.rules;

import br.com.alexpfx.supermarket.domain.MeasureUnit;
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
        return descricao(p);
    }

    private String descricao(Element p) {
        return p.select("span.descr").text().trim();
    }


    @Override
    protected String extractUnidadeMedida(Element p) {
        String descricao = descricao(p);
        return MeasureUnit.Patterns.QUANTITY_UNIT.getUnity(descricao);

    }

    @Override
    protected String extractQuantidade(Element p) {
        String descricao = descricao(p);
        return MeasureUnit.Patterns.QUANTITY_UNIT.getQuantity(descricao);
    }


    @Override
    protected String extractUrl(Element p) {
        return p.select("span.descr a").attr("abs:href").trim();

    }


}

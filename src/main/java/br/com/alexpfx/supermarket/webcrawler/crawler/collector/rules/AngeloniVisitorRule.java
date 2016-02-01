package br.com.alexpfx.supermarket.webcrawler.crawler.collector.rules;

import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.CrawlerAPI;
import com.google.common.base.Preconditions;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 25/01/2016.
 */
public class AngeloniVisitorRule extends AbstractExtractionRules <String, CrawlerAPI<Document>> {

    public static final String BASE_URI = "http://www.angeloni.com.br/";

    public AngeloniVisitorRule(){

    }

    public AngeloniVisitorRule(CrawlerAPI<Document> parserAPI) {
        setCrawlerAPI(parserAPI);
    }

    @Override
    public List<String> extract(String htmlCode) {
        Preconditions.checkNotNull(getCrawlerAPI());

        List<String> list = new ArrayList<String>();
        Document doc = getCrawlerAPI().parse(htmlCode);
        doc.setBaseUri(BASE_URI);

        org.jsoup.select.Elements elements = doc.select("a.lnkTp01 ");
        org.jsoup.select.Elements lnkTp02 = doc.select("a.lnkTp02 ");
        elements.addAll(lnkTp02);

        elements.forEach(element1 -> {
            String href = element1.attr("abs:href");
            list.add(href);
        });
        return list;

    }
}


package br.com.alexpfx.supermarket.webcrawler.crawler.api.parse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.nio.charset.Charset;

import static br.com.alexpfx.supermarket.webcrawler.crawler.api.Constants.CHARSET;

/**
 * Created by alexandre on 30/01/2016.
 */
public class JSoupParseAPI implements ParserAPI<Document> {

    @Override
    public Document parseDocument(String htmlCode) {
        Document doc = Jsoup.parse(htmlCode);
        doc.charset(Charset.forName(CHARSET));
        return doc;
    }
}

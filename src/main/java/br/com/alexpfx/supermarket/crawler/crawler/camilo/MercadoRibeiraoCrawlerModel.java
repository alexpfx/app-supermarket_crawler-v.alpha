package br.com.alexpfx.supermarket.crawler.crawler.camilo;

import br.com.alexpfx.supermarket.crawler.crawler.AlreadyVisitUrls;
import br.com.alexpfx.supermarket.crawler.crawler.CrawlerModel;
import br.com.alexpfx.supermarket.crawler.model.ExtractProductError;
import br.com.alexpfx.supermarket.crawler.model.to.ProductInfoTO;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by alexandre on 12/12/2015.
 */
public class MercadoRibeiraoCrawlerModel implements CrawlerModel {
    AlreadyVisitUrls urls = new AlreadyVisitUrls();

    @Override
    public boolean shouldVisit(Page page, WebURL webURL) {
        String url = webURL.getURL().toLowerCase();
        return (!urls.contains(url) && url.contains("mercadoribeirao") && (url.contains("produtos.php") || url.contains("produto.php")));
    }

    @Override
    public ProductInfoTO extractProduct(Page page) throws ExtractProductError {
        urls.add(page.getWebURL().getURL());
        HtmlParseData parseData = (HtmlParseData) page.getParseData();
        String html = parseData.getHtml();
        Document document = Jsoup.parse(html);
        String descricao = extractDescricao(document);
        String cod = extractCodigo(document);
        return ProductInfoTO.of(cod, descricao);
    }

    private String extractCodigo(Document document) {
        Element new_cont = document.getElementById("new_cont");
        Elements category = new_cont.getElementsByClass("category");
        return category.text();
    }

    private String extractDescricao(Document document) {
        Element new_cont = document.getElementById("new_cont");
        Elements viewbox = new_cont.getElementsByClass("view-box");
        return viewbox.text();
    }

//    private String extractDescricao(Document document) {
//        Element boxTtl = document.getElementById("boxTtl");
//        Elements sIfr = boxTtl.getElementsByClass("sIfr");
//        return sIfr.text();
//    }


    @Override
    public boolean isProductPage(String url) {
        return url.contains("produto.php") && url.contains("id_prod");
    }
}

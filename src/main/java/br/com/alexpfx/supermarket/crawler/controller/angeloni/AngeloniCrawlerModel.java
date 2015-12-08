package br.com.alexpfx.supermarket.crawler.controller.angeloni;

import br.com.alexpfx.supermarket.crawler.DigitOrComma;
import br.com.alexpfx.supermarket.crawler.controller.CrawlerModel;
import br.com.alexpfx.supermarket.crawler.model.ExtractProductError;
import br.com.alexpfx.supermarket.crawler.model.ProductInfo;
import br.com.alexpfx.supermarket.crawler.model.SimpleProductInfo;
import com.google.common.base.CharMatcher;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * Created by alexandre on 06/12/2015.
 */
public class AngeloniCrawlerModel implements CrawlerModel {
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp3|zip|gz))$");

    AlreadyVisitUrls urls = new AlreadyVisitUrls();

    public boolean shouldVisit(Page page, WebURL webURL) {
        String href = webURL.getURL().toLowerCase();
        System.out.println("Quantidade urls: "+urls.size());
        return (href.contains("idProduto=") || href.contains("grupo=") || !urls.contains(href));
    }

    public ProductInfo extractProduct(Page page) throws ExtractProductError {
        urls.add(page.getWebURL().getURL());
        WebURL webURL = page.getWebURL();
        HtmlParseData parseData = (HtmlParseData) page.getParseData();
        String html = parseData.getHtml();
        Document document = Jsoup.parse(html);
        String descricao = extractDescricao(document);

        Element rgtProd = document.getElementById("rgtProd");
        String codigo = extractCodigo(rgtProd);
        BigDecimal price = extractPrice(rgtProd);
        return SimpleProductInfo.of(codigo, descricao, price);

    }

    private BigDecimal extractPrice(Element rgtProd) {
        Element prodPrice = rgtProd.getElementById("product-price");
        Elements valorPrice = prodPrice.getElementsByClass("valorPrice");
        String valor = CharMatcher.forPredicate(new DigitOrComma()).retainFrom(valorPrice.text()).replace(',', '.');
        return new BigDecimal(valor);
    }

    private String extractCodigo(Element rgtProd) {
        Elements ref = rgtProd.getElementsByClass("ref");
        String codigo = CharMatcher.DIGIT.retainFrom(ref.text());
        return codigo;
    }

    private String extractDescricao(Document document) {
        Element boxTtl = document.getElementById("boxTtl");
        Elements sIfr = boxTtl.getElementsByClass("sIfr");
        return sIfr.text();
    }

    public boolean isProductPage(String url) {
        return url.contains("idProduto=");
    }
}

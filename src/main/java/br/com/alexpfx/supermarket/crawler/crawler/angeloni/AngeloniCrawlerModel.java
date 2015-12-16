package br.com.alexpfx.supermarket.crawler.crawler.angeloni;

import br.com.alexpfx.supermarket.crawler.DigitOrComma;
import br.com.alexpfx.supermarket.crawler.crawler.AlreadyVisitUrls;
import br.com.alexpfx.supermarket.crawler.crawler.CrawlerModel;
import br.com.alexpfx.supermarket.crawler.model.ExtractProductError;
import br.com.alexpfx.supermarket.crawler.model.to.ProductInfoTO;
import br.com.alexpfx.supermarket.crawler.model.to.ProductInfoTOBuilder;
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

    AlreadyVisitUrls urls = new AlreadyVisitUrls();

    public boolean shouldVisit(Page page, WebURL webURL) {
        String href = webURL.getURL().toLowerCase();
        return (!urls.contains(href) && href.contains("super") && (href.contains("grupo") || href.contains("idProduto")));
    }

    public ProductInfoTO extractProduct(Page page) throws ExtractProductError {
        String url = page.getWebURL().getURL();
        urls.add(url);
        HtmlParseData parseData = (HtmlParseData) page.getParseData();
        String html = parseData.getHtml();
        Document document = Jsoup.parse(html);
        String descricao = extractDescricao(document);

        Element rgtProd = document.getElementById("rgtProd");
        String codigo = extractCodigo(rgtProd);
        BigDecimal price = extractPrice(rgtProd);

        return new ProductInfoTOBuilder().description(descricao).id(codigo).price(price).url(url).createProductInfoTO();

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

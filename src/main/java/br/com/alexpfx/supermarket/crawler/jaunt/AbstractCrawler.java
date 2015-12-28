package br.com.alexpfx.supermarket.crawler.jaunt;

import br.com.alexpfx.supermarket.crawler.model.bo.ProductBo;
import br.com.alexpfx.supermarket.crawler.model.domain.Product;
import com.jaunt.Document;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by alexandre on 27/12/2015.
 */
public abstract class AbstractCrawler implements Crawler {

    @Autowired
    ProductBo productBo;

    abstract List<String> extractSubPages(Document document);

    private String rootUrl;

    private UserAgent userAgent;

    public AbstractCrawler(UserAgent userAgent, String rootUrl) {
        this.userAgent = userAgent;
        this.rootUrl = rootUrl;
    }

    @Override
    public void crawl() {
        try {
            System.out.println(productBo);
            Document doc = userAgent.visit(rootUrl);
            List<String> links = extractSubPages(doc);

            links.forEach(s -> {
                try {
                    List<Product> products = extractProducts(userAgent.visit(s));
                } catch (ResponseException e) {
                    e.printStackTrace();
                }
            });

        } catch (ResponseException e) {
            e.printStackTrace();
        }
    }

    protected abstract List<Product> extractProducts(Document visit);


}

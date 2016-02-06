package br.com.alexpfx.supermarket.webcrawler.crawler.collector.rules;

import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.CrawlerAPI;
import br.com.alexpfx.supermarket.webcrawler.crawler.collector.ExtractionRules;
import br.com.alexpfx.supermarket.webcrawler.to.ProdutoSuperMercadoTO;
import br.com.alexpfx.supermarket.webcrawler.to.ProdutoSuperMercadoTOBuilder;
import com.google.common.base.Preconditions;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 26/01/2016.
 */
public abstract class ProductExtractorFromPageList <T, X> extends AbstractExtractionRules <ProdutoSuperMercadoTO, CrawlerAPI<Document>>{


    public ProductExtractorFromPageList(
            CrawlerAPI<Document> crawlerAPI) {
        super(crawlerAPI);
    }

    @Override
    public List<ProdutoSuperMercadoTO> extract(String htmlCode) {
//        Preconditions.checkNotNull(getCrawlerAPI());

        Document doc = getCrawlerAPI().parse(htmlCode);

        Elements list = extractItemList(doc);
        List<ProdutoSuperMercadoTO> pList = new ArrayList<>();
        list.forEach(p -> {
            ProdutoSuperMercadoTOBuilder builder = new ProdutoSuperMercadoTOBuilder();
            ProdutoSuperMercadoTO produtoSuperMercadoTO = builder.fabricante(extractFabricante(p)).url(
                    extractUrl(p)).code(extractCodigo(p)).descricao(extractDescricao(p)).precoFinal(
                    extractPrecoFinal(p)).precoOriginal(extractPrecoOriginal(p)).quantidade(
                    extractQuantidade(p)).unidadeMedida(extractUnidadeMedida(p)).create();
            pList.add(produtoSuperMercadoTO);
        });

        return pList;
    }

    protected abstract String extractQuantidade(Element p);

    protected abstract String extractPrecoOriginal(Element p);

    protected abstract String extractPrecoFinal(Element p);

    protected abstract String extractUnidadeMedida(Element p);

    protected abstract String extractDescricao(Element p);

    protected abstract String extractCodigo(Element p);

    protected abstract String extractUrl(Element p);

    protected abstract String extractFabricante(Element p);

    public abstract Elements extractItemList(Document doc);


}

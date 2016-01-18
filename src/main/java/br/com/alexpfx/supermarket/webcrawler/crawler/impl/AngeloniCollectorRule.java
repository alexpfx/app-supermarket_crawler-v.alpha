package br.com.alexpfx.supermarket.webcrawler.crawler.impl;

import br.com.alexpfx.supermarket.webcrawler.crawler.CollectorRule;
import br.com.alexpfx.supermarket.webcrawler.to.ProdutoSuperMercadoTO;
import com.jaunt.Document;

import java.util.List;

/**
 * Created by alexandre on 18/01/2016.
 */
public class AngeloniCollectorRule implements CollectorRule<ProdutoSuperMercadoTO> {

    @Override
    public List<ProdutoSuperMercadoTO> evaluate(Document doc) {
        return null;
    }
}

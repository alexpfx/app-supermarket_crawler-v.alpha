package br.com.alexpfx.supermarket.batch.processor;

import br.com.alexpfx.supermarket.domain.Manufacturer;
import br.com.alexpfx.supermarket.domain.MeasureUnit;
import br.com.alexpfx.supermarket.domain.Product;
import br.com.alexpfx.supermarket.domain.ProductBuilder;
import br.com.alexpfx.supermarket.domain.barcode.BarCodeFactory;
import br.com.alexpfx.supermarket.domain.barcode.Ean13;
import br.com.alexpfx.supermarket.domain.barcode.Ean13Factory;
import br.com.alexpfx.supermarket.webcrawler.exception.InvalidBarCodeException;
import br.com.alexpfx.supermarket.webcrawler.to.ProdutoSuperMercadoTO;
import br.com.alexpfx.supermarket.webcrawler.to.TransferObject;
import org.springframework.batch.item.ItemProcessor;

import java.util.logging.Logger;

/**
 * Created by alexandre on 10/01/2016.
 */
public class ProductProcessor implements ItemProcessor<TransferObject, Product> {
    Logger LOG = Logger.getLogger(ProductProcessor.class.getSimpleName());

    @Override
    public Product process(TransferObject transferObject) {
        ProdutoSuperMercadoTO to = (ProdutoSuperMercadoTO) transferObject;
        //Todo: barcode Factory
        BarCodeFactory ean13Factory = new Ean13Factory();
        Ean13 ean13 = null;
        try {
            ean13 = (Ean13) ean13Factory.create(to.getCodigo());
        } catch (InvalidBarCodeException e) {
            LOG.warning("invalid ean: " + e.getCode());
        }
        Manufacturer manufacturer = new Manufacturer(to.getFabricante());
        return new ProductBuilder().description(to.getDescricao()).url(to.getUrl()).ean(ean13).manufacturer(
                manufacturer).manufacturer(manufacturer).measureUnit(MeasureUnit.valueOf(to.getUnidadeMedida())).createProduct();
    }
}

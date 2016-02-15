package br.com.alexpfx.supermarket.batch.processor;

import br.com.alexpfx.supermarket.domain.Manufacturer;
import br.com.alexpfx.supermarket.domain.MeasureUnit;
import br.com.alexpfx.supermarket.domain.Product;
import br.com.alexpfx.supermarket.domain.ProductBuilder;
import br.com.alexpfx.supermarket.domain.barcode.BarCode;
import br.com.alexpfx.supermarket.domain.barcode.Ean13Factory;
import br.com.alexpfx.supermarket.webcrawler.exception.InvalidBarCodeException;
import br.com.alexpfx.supermarket.webcrawler.to.ProdutoSuperMercadoTO;
import br.com.alexpfx.supermarket.webcrawler.to.TransferObject;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by alexandre on 07/02/2016.
 */
public class AngeloniProductProcessor implements ItemProcessor<TransferObject, Product> {
    @Override
    public Product process(TransferObject transferObject) {
        ProdutoSuperMercadoTO to = (ProdutoSuperMercadoTO) transferObject;
        BarCode ean13 = null;
        try {
            ean13 = new Ean13Factory().create(to.getCodigo());
        } catch (InvalidBarCodeException e) {
            ean13 = null;
        }
        ProductBuilder productBuilder = new ProductBuilder();

        Product product = productBuilder.description(to.getDescricao()).manufacturer(
                new Manufacturer(to.getFabricante())).measureUnit(MeasureUnit.getByAcronym(to.getUnidadeMedida())
        ).url(to.getUrl()).createProduct();
        return product;
    }
}

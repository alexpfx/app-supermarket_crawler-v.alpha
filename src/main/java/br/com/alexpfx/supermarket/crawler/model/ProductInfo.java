package br.com.alexpfx.supermarket.crawler.model;

import java.math.BigDecimal;

/**
 * Created by alexandre on 04/12/2015.
 */
public interface ProductInfo {

    BigDecimal getPrice();

    String getDescription();

    String getIdentity();

    QuantityMeasurement getQuantity();

}

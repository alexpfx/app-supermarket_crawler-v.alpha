package br.com.alexpfx.supermarket.webcrawler.to;

/**
 * Created by alexandre on 07/01/2016.
 */
public class ProdutoSuperMercadoTO implements TransferObject {

    private String url;
    private String description;
    private String finalPrice ;
    private String originalPrice;
    private String measureUnity;
    private String quantity;
    private String codigo;

    public ProdutoSuperMercadoTO() {
    }

    public ProdutoSuperMercadoTO(String url, String description, String finalPrice, String originalPrice, String measureUnity, String quantity, String codigo) {
        this.url = url;
        this.description = description;
        this.finalPrice = finalPrice;
        this.originalPrice = originalPrice;
        this.measureUnity = measureUnity;
        this.quantity = quantity;
        this.codigo = codigo;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public String getMeasureUnity() {
        return measureUnity;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getCodigo() {
        return codigo;
    }
}

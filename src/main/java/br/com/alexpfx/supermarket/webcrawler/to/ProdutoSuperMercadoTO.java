package br.com.alexpfx.supermarket.webcrawler.to;

/**
 * Created by alexandre on 07/01/2016.
 */
public class ProdutoSuperMercadoTO implements TransferObject {

    private String url;
    private String descricao;
    private String precoFinal;
    private String precoOriginal;
    private String unidadeMedida;
    private String quantidade;
    private String codigo;
    private String fabricante;

    public ProdutoSuperMercadoTO() {
    }

    ProdutoSuperMercadoTO(String url, String descricao, String precoFinal, String precoOriginal, String unidadeMedida,
                          String quantidade, String codigo, String fabricante) {
        this.url = url;
        this.descricao = descricao;
        this.precoFinal = precoFinal;
        this.precoOriginal = precoOriginal;
        this.unidadeMedida = unidadeMedida;
        this.quantidade = quantidade;
        this.codigo = codigo;
        this.fabricante = fabricante;
    }

    public String getUrl() {
        return url;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPrecoFinal() {
        return precoFinal;
    }

    public String getPrecoOriginal() {
        return precoOriginal;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getFabricante() {
        return fabricante;
    }
}

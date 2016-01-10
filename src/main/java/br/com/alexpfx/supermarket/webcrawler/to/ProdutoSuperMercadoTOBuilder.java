package br.com.alexpfx.supermarket.webcrawler.to;

public class ProdutoSuperMercadoTOBuilder {
    private String url;
    private String descricao;
    private String precoFinal;
    private String precoOriginal;
    private String unidadeMedida;
    private String quantidade;
    private String codigo;

    public ProdutoSuperMercadoTOBuilder url(String url) {
        this.url = url;
        return this;
    }

    public ProdutoSuperMercadoTOBuilder descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public ProdutoSuperMercadoTOBuilder precoFinal(String precoFinal) {
        this.precoFinal = precoFinal;
        return this;
    }

    public ProdutoSuperMercadoTOBuilder precoOriginal(String precoOriginal) {
        this.precoOriginal = precoOriginal;
        return this;
    }

    public ProdutoSuperMercadoTOBuilder unidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
        return this;
    }

    public ProdutoSuperMercadoTOBuilder quantidade(String quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public ProdutoSuperMercadoTOBuilder code(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public ProdutoSuperMercadoTO create() {
        return new ProdutoSuperMercadoTO(url, descricao, precoFinal, precoOriginal, unidadeMedida, quantidade, codigo);
    }
}
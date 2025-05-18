package com.example.postech_tc4_pedido.gateway.database.entity;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

public class ProdutoEntity {
    @Id
    private String sku;
    private int quantidade;
    private String nome;
    private String codigoDeBarras;
    private String descricao;
    private String fabricante;
    private BigDecimal preco;
    private String categoria;

    public ProdutoEntity() {
        super();
    }

    public ProdutoEntity(String sku, int quantidade, String nome, String codigoDeBarras, String descricao, String fabricante,
                         BigDecimal preco, String categoria) {
        super();
        this.sku = sku;
        this.quantidade = quantidade;
        this.nome = nome;
        this.codigoDeBarras = codigoDeBarras;
        this.descricao = descricao;
        this.fabricante = fabricante;
        this.preco = preco;
        this.categoria = categoria;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}

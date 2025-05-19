package com.example.postech_tc4_pedido.domain;

import com.example.postech_tc4_pedido.exception.QuantidadeProdutoException;

import java.math.BigDecimal;

public class Produto {
    private final String sku;
    private final int quantidade;
    private final String nome;
    private final String codigoDeBarras;
    private final String descricao;
    private final String fabricante;
    private final BigDecimal preco;
    private final String categoria;

    public Produto(String sku, int quantidade, String nome, String codigoDeBarras,
                   String descricao, String fabricante, BigDecimal preco, String categoria) {
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

    public int getQuantidade() {
        return quantidade;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getFabricante() {
        return fabricante;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void validaQuantidadeProduto(int quantidadeProduto) throws QuantidadeProdutoException {
        if (quantidadeProduto < 0) {
            throw new QuantidadeProdutoException("A quantidade do produto não pode ser zerada ou negativa.");
        }
    }
}
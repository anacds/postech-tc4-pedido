package com.example.postech_tc4_pedido.gateway.database.entity;

public class PagamentoEntity {
    private String numeroCartao;

    public PagamentoEntity() {
        super();
    }

    public PagamentoEntity(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
}

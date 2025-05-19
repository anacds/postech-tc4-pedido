package com.example.postech_tc4_pedido.domain;

public class Pagamento {
    private final String numeroCartao;

    public Pagamento(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }
}
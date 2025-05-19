package com.example.postech_tc4_pedido.domain;

import java.math.BigDecimal;

public class SolicitacaoPagamento {
    private final String pedidoId;
    private final BigDecimal valorTotal;
    private final String numeroCartao;

    public SolicitacaoPagamento(String pedidoId, BigDecimal valorTotal, String numeroCartao) {
        this.pedidoId = pedidoId;
        this.valorTotal = valorTotal;
        this.numeroCartao = numeroCartao;
    }

    public String getPedidoId() {
        return pedidoId;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }
}
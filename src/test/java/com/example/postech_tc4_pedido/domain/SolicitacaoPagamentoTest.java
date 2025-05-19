package com.example.postech_tc4_pedido.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolicitacaoPagamentoTest {

    @Test
    void deveCriarSolicitacaoPagamentoComDadosCorretos() {
        String pedidoId = "pedido123";
        BigDecimal valorTotal = BigDecimal.valueOf(150.75);
        String numeroCartao = "4111111111111111";

        SolicitacaoPagamento solicitacao = new SolicitacaoPagamento(pedidoId, valorTotal, numeroCartao);

        assertEquals(pedidoId, solicitacao.getPedidoId());
        assertEquals(valorTotal, solicitacao.getValorTotal());
        assertEquals(numeroCartao, solicitacao.getNumeroCartao());
    }
}
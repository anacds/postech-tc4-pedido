package com.example.postech_tc4_pedido.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PagamentoTest {

    @Test
    void deveCriarPagamentoComNumeroCartao() {
        Pagamento pagamento = new Pagamento("4111111111111111");

        assertEquals("4111111111111111", pagamento.getNumeroCartao());
    }
}
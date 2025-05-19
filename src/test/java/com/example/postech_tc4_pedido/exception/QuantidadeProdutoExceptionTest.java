package com.example.postech_tc4_pedido.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuantidadeProdutoExceptionTest {

    @Test
    void deveRetornarMensagemPadrao() {
        QuantidadeProdutoException ex = new QuantidadeProdutoException();
        assertEquals("A quantidade do produto não pode ser zerada ou negativa.", ex.getMessage());
    }
}
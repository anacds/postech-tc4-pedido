package com.example.postech_tc4_pedido.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuantidadeProdutoExceptionTest {

    @Test
    void deveLancarExcecaoComMensagemPadrao() {
        QuantidadeProdutoException exception = assertThrows(
                QuantidadeProdutoException.class,
                () -> { throw new QuantidadeProdutoException("qualquer coisa"); }
        );

        assertEquals("A quantidade do produto não pode ser zerada ou negativa.", exception.getMessage());
    }
}
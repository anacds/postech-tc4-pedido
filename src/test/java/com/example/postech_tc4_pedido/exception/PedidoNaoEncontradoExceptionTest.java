package com.example.postech_tc4_pedido.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoNaoEncontradoExceptionTest {

    @Test
    void deveCriarExceptionComMensagemCorreta() {
        String pedidoId = "abc123";
        PedidoNaoEncontradoException exception = new PedidoNaoEncontradoException(pedidoId);

        assertEquals("O pedido não foi encontrado: abc123", exception.getMessage());
    }
}
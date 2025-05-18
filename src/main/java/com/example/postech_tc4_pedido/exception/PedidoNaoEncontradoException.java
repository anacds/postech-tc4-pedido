package com.example.postech_tc4_pedido.exception;

public class PedidoNaoEncontradoException extends RuntimeException {
    public PedidoNaoEncontradoException(String pedidoId) {
        super("O pedido não foi encontrado: " + pedidoId);
    }
}

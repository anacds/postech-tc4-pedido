package com.example.postech_tc4_pedido.exception;

public class QuantidadeProdutoException extends RuntimeException {
    public QuantidadeProdutoException() {
        super("A quantidade do produto não pode ser zerada ou negativa.");
    }
}
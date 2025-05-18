package com.example.postech_tc4_pedido.gateway.external.interfaces;

public interface IEstoqueGateway {
    int verificarQuantidadeDisponivel(String sku);
    void removerDoEstoque(String sku, int quantidade);
    void reporEstoque(String sku, int quantidade);
}
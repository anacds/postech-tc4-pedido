package com.example.postech_tc4_pedido.gateway.external.interfaces;

import com.example.postech_tc4_pedido.dto.ProdutoDTO;

public interface IProdutoGateway {
    ProdutoDTO buscarProdutoPorSku(String sku);
}
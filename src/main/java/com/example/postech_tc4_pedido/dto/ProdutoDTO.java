package com.example.postech_tc4_pedido.dto;

import java.math.BigDecimal;

public record ProdutoDTO (String sku,
                          int quantidade,
                          String nome,
                          String codigoBarras,
                          String descricao,
                          String fabricante,
                          BigDecimal preco,
                          String categoria) {
}

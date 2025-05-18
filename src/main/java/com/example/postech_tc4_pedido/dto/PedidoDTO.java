package com.example.postech_tc4_pedido.dto;

import com.example.postech_tc4_pedido.domain.StatusPedidoEnum;

import java.math.BigDecimal;
import java.util.List;

public record PedidoDTO (String id,
                         String idEvento,
                         ClienteDTO cliente,
                        List<ProdutoDTO> produtos,
                        PagamentoDTO dadosPagamento,
                        StatusPedidoEnum status,
                         BigDecimal valorTotalPedido) {
}

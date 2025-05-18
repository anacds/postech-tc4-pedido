package com.example.postech_tc4_pedido.dto;

import com.example.postech_tc4_pedido.domain.StatusPedidoEnum;

public record RetornoPagamento (String idPedido, StatusPedidoEnum statusPagamento) {
}

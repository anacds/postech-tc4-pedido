package com.example.postech_tc4_pedido.dto;

import java.math.BigDecimal;

public record SolicitacaoPagamentoDTO (String pedidoId, BigDecimal valorTotal, String numeroCartao) {
}

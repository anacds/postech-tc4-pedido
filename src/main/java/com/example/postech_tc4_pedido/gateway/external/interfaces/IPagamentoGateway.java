package com.example.postech_tc4_pedido.gateway.external.interfaces;

import com.example.postech_tc4_pedido.dto.SolicitacaoPagamentoDTO;

public interface IPagamentoGateway {
    void solicitarPagamento(SolicitacaoPagamentoDTO solicitacaoPagamentoDTO);
}
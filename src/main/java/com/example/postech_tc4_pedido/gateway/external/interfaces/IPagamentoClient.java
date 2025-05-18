package com.example.postech_tc4_pedido.gateway.external.interfaces;

import com.example.postech_tc4_pedido.dto.SolicitacaoPagamentoDTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface IPagamentoClient {
    void solicitarPagamento(@RequestBody SolicitacaoPagamentoDTO solicitacaoPagamentoDTO);
}

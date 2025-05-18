package com.example.postech_tc4_pedido.gateway.external.interfaces.client;

import com.example.postech_tc4_pedido.dto.SolicitacaoPagamentoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "pagamentoClient", url = "${pagamento.service.url}")
public interface PagamentoClient {

    @PostMapping("/pagamentos")
    void solicitarPagamento(@RequestBody SolicitacaoPagamentoDTO solicitacaoPagamentoDTO);
}
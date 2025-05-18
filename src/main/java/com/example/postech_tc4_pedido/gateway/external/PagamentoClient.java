package com.example.postech_tc4_pedido.gateway.external;

import com.example.postech_tc4_pedido.dto.SolicitacaoPagamentoDTO;
import com.example.postech_tc4_pedido.gateway.external.interfaces.IPagamentoClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "pagamentoClient", url = "${pagamento.service.url}")
public interface PagamentoClient extends IPagamentoClient {

    @Override
    @PostMapping("/pagamentos")
    void solicitarPagamento(@RequestBody SolicitacaoPagamentoDTO solicitacaoPagamentoDTO);

}
